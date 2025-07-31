package com.project.minehair.domain.auth.application.service

import com.project.minehair.domain.auth.adapter.`in`.web.dto.LoginRequest
import com.project.minehair.domain.auth.application.port.`in`.AuthUseCase
import com.project.minehair.domain.auth.application.port.out.AuthDomainPort
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class AuthService(
    private val userDomainPort: AuthDomainPort,
//    private val authenticationManager: AuthenticationManager
): AuthUseCase {

    override fun login(loginRequest: LoginRequest) {

        // 1. 사용자 조회
        userDomainPort.getUserByUserId(loginRequest.userId)
            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)

        // 2. 인증처리
//        val authentication = authenticationManager.authenticate(
//            UsernamePasswordAuthenticationToken(member.id, "")
//        )
//        SecurityContextHolder.getContext().authentication = authentication

        // 3. token 생성

        // 4. redis에 토큰 저장
    }

}