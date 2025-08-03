package com.project.minehair.domain.auth.application.port.`in`

import com.project.minehair.domain.auth.adapter.`in`.web.dto.LoginRequest
import com.project.minehair.domain.auth.adapter.`in`.web.dto.LoginResponse

interface AuthUseCase {
    // 로그인
    fun login(loginRequest: LoginRequest): LoginResponse
}