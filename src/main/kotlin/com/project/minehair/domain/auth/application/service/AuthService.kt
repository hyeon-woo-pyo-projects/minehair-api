package com.project.minehair.domain.auth.application.service

import com.project.minehair.domain.auth.adapter.`in`.web.dto.LoginRequest
import com.project.minehair.domain.auth.adapter.`in`.web.dto.LoginResponse
import com.project.minehair.domain.auth.application.port.`in`.AuthUseCase
import com.project.minehair.domain.auth.application.port.out.AuthDomainPort
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.utils.JwtUtil
import com.project.minehair.global.utils.RedisUtil
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Transactional(readOnly = true)
@Service
class AuthService(
    private val userDomainPort: AuthDomainPort,
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil,
    private val redisUtil: RedisUtil
) : AuthUseCase {

    override fun login(loginRequest: LoginRequest): LoginResponse {

        var authorities: List<String>

        // 1. Spring Security 활용한 인증 처리
        try {
            val authenticationToken = UsernamePasswordAuthenticationToken(
                loginRequest.userId,
                loginRequest.password
            )

            val authentication = authenticationManager.authenticate(authenticationToken)

            // 인증 성공 후 SecurityContext에 저장
            SecurityContextHolder.getContext().authentication = authentication

            // 인증된 사용자의 권한 정보 가져오기
            authorities = authentication.authorities.map { it.authority }

        } catch (ex: BadCredentialsException) {
            throw BusinessException(ErrorCode.INVALID_CREDENTIALS)
        } catch (ex: Exception) {
            throw BusinessException(ErrorCode.AUTHENTICATION_FAILED)
        }

        // 2. token 생성
        val accessToken = jwtUtil.generateAccessToken(loginRequest.userId, authorities)
        val refreshToken = jwtUtil.generateRefreshToken(loginRequest.userId)

        // 3. redis에 토큰 저장
        redisUtil.setRedisValue(
            "user:${loginRequest.userId}:accessToken",
            accessToken,
            jwtUtil.getAccessTokenExpiration(),
            TimeUnit.MINUTES
        )
        redisUtil.setRedisValue(
            "user:${loginRequest.userId}:refreshToken",
            refreshToken,
            jwtUtil.getRefreshTokenExpiration(),
            TimeUnit.MINUTES
        )

        return LoginResponse(
            accessToken = accessToken,
            refreshToken = refreshToken,
            authorities.first()
        )
    }

}