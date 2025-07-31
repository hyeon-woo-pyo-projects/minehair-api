package com.project.minehair.domain.auth.adapter.`in`.web

import com.project.minehair.domain.auth.adapter.`in`.web.dto.LoginRequest
import com.project.minehair.domain.auth.application.port.`in`.AuthUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "인증/인가 API", description = "인증/인가 API")
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authUseCase: AuthUseCase
) {

    @Operation(summary = "로그인", description = "로그인")
    @PostMapping("/login")
    fun login(@RequestBody loginRequest: LoginRequest): BaseResponse<Nothing?> {
        authUseCase.login(loginRequest)
        return BaseResponse.ok()
    }

}