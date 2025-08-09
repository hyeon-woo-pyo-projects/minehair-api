package com.project.minehair.global.filter

import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.filter.context.JwtTokenContext
import com.project.minehair.global.filter.dto.JwtTokenInfo
import com.project.minehair.global.utils.JwtUtil
import com.project.minehair.global.utils.RedisUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter


@Component
class JwtAuthenticationFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService,
    private val redisUtil: RedisUtil
) : OncePerRequestFilter() {

    private val log: Logger = LoggerFactory.getLogger(JwtAuthenticationFilter::class.java)

    override fun shouldNotFilter(request: HttpServletRequest): Boolean {
        val path = request.requestURI

        return when {
            path.startsWith("/api/auth") -> true
            path.startsWith("/health") -> true
            path.startsWith("/api/user") && request.method == "POST" -> true
            path.startsWith("/api/banner") && request.method == "GET" -> true
            // 개발 도구들
            // path.startsWith("/h2-console/") -> true
            // path.startsWith("/v3/api-docs/") -> true
            // path.startsWith("/swagger-ui/") -> true
            // path.startsWith("/swagger-resources/") -> true
            // path.startsWith("/webjars/") -> true
            else -> false
        }
    }

    /**
     * 토큰이 없어도 허용되는 경로인지 확인
     */
    private fun isOptionalTokenPath(request: HttpServletRequest): Boolean {
        val path = request.requestURI
        val method = request.method

        return when {
            path.startsWith("/api/role-menus") && method == "GET" -> true
            // 다른 선택적 토큰 경로들 추가 가능
            else -> false
        }
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // 기존 컨텍스트 정리
            JwtTokenContext.clear()

            // 1. Authorization 헤더에서 토큰 추출
            val jwt = getJwtFromRequest(request)

            // 2. 토큰이 없는 경우 처리
            if (jwt == null) {
                if (isOptionalTokenPath(request)) {
                    // 토큰이 선택적인 경로는 토큰 없이도 진행
                    log.info("선택적 토큰 경로로 토큰 없이 진행: ${request.requestURI}")
                } else {
                    // 토큰이 필수인 경로는 예외 발생
                    throw BusinessException(ErrorCode.UNAUTHORIZED, "JWT token not found in Authorization header")
                }
            } else {
                // 3. 토큰이 있는 경우 유효성 검증 및 인증 처리
                processTokenAuthentication(jwt, request)
            }

        } catch (e: BusinessException) {
            log.warn("JWT 인증 실패: ${e.message}")
            SecurityContextHolder.clearContext()
            JwtTokenContext.clear()
        } catch (e: Exception) {
            log.error("JWT 필터 처리 중 예상치 못한 오류 발생: ${e.message}", e)
            SecurityContextHolder.clearContext()
            JwtTokenContext.clear()
        }

        try {
            // 4. 다음 필터 체인으로 진행
            filterChain.doFilter(request, response)
        } finally {
            // 요청 완료 후 컨텍스트 정리 (메모리 누수 방지)
            JwtTokenContext.clear()
        }
    }

    /**
     * 토큰 인증 처리
     */
    private fun processTokenAuthentication(jwt: String, request: HttpServletRequest) {
        // 토큰 유효성 검증
        if (!jwtUtil.validateToken(jwt)) {
            throw BusinessException(ErrorCode.INVALID_TOKEN, "Invalid JWT token")
        }

        // 토큰에서 사용자 ID 추출
        val userId = jwtUtil.getUserIdFromToken(jwt)

        // Redis에서 토큰 검증 (토큰 탈취 방지)
        val storedToken = redisUtil.getRedisValue("user:${userId}:accessToken")
            ?: throw BusinessException(ErrorCode.INVALID_TOKEN, "No stored token found for user: $userId")

        if (storedToken != jwt) {
            throw BusinessException(ErrorCode.INVALID_TOKEN, "Token mismatch for user: $userId")
        }

        if (!jwtUtil.isAccessToken(jwt)) {
            throw BusinessException(ErrorCode.INVALID_TOKEN, "Token is not an access token")
        }

        // 사용자 정보 로드
        val userDetails = userDetailsService.loadUserByUsername(userId)

        // JWT 토큰 정보 객체 생성 및 컨텍스트에 저장
        val tokenInfo = JwtTokenInfo(
            token = jwt,
            userId = userId,
            authorities = userDetails.authorities.map { it.authority },
            issuedAt = jwtUtil.getIssuedAtFromTokenAsLong(jwt),
            expiresAt = jwtUtil.getExpirationFromTokenAsLong(jwt)
        )
        JwtTokenContext.setToken(tokenInfo)

        // Spring Security 인증 객체 생성
        val authentication = UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.authorities
        )
        authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

        // SecurityContext에 인증 정보 저장
        SecurityContextHolder.getContext().authentication = authentication

        log.info("JWT 인증 성공: userId=$userId")
    }

    /**
     * Authorization 헤더에서 JWT 토큰 추출
     */
    private fun getJwtFromRequest(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader("Authorization")
        return if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            bearerToken.substring(7) // "Bearer " 제거
        } else {
            null
        }
    }
}