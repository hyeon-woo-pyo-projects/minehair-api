package com.project.minehair.global.filter

import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
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

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        try {
            // 1. Authorization 헤더에서 토큰 추출
            val jwt = getJwtFromRequest(request)
                ?: throw BusinessException(ErrorCode.UNAUTHORIZED, "JWT token not found in Authorization header")

            // 2. 토큰 유효성 검증
            if (!jwtUtil.validateToken(jwt)) {
                throw BusinessException(ErrorCode.INVALID_TOKEN, "Invalid JWT token")
            }

            // 3. 토큰에서 사용자 ID 추출
            val userId = jwtUtil.getUserIdFromToken(jwt)

            // 4. Redis에서 토큰 검증 (토큰 탈취 방지)
            val storedToken = redisUtil.getRedisValue("user:${userId}:accessToken")
                ?: throw BusinessException(ErrorCode.INVALID_TOKEN, "No stored token found for user: $userId")

            if (storedToken != jwt) {
                throw BusinessException(ErrorCode.INVALID_TOKEN, "Token mismatch for user: $userId")
            }

            if (!jwtUtil.isAccessToken(jwt)) {
                throw BusinessException(ErrorCode.INVALID_TOKEN, "Token is not an access token")
            }

            // 5. 사용자 정보 로드
            val userDetails = userDetailsService.loadUserByUsername(userId)

            // 6. Spring Security 인증 객체 생성
            val authentication = UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.authorities
            )
            authentication.details = WebAuthenticationDetailsSource().buildDetails(request)

            // 7. SecurityContext에 인증 정보 저장
            SecurityContextHolder.getContext().authentication = authentication

            log.info("JWT 인증 성공: userId=$userId")

        } catch (e: BusinessException) {
            log.warn("JWT 인증 실패: ${e.message}")
            SecurityContextHolder.clearContext()
        } catch (e: Exception) {
            log.error("JWT 필터 처리 중 예상치 못한 오류 발생: ${e.message}", e)
            SecurityContextHolder.clearContext()
        }
        // 8. 다음 필터 체인으로 진행
        filterChain.doFilter(request, response)
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