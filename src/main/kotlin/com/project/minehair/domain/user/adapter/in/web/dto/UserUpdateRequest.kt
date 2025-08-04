package com.project.minehair.domain.user.adapter.`in`.web.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

// 사용자 수정 요청 DTO
data class UserUpdateRequest(
    @field:NotBlank(message = "이메일은 필수입니다.")
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "이름은 필수입니다.")
    @field:Size(min = 2, max = 10, message = "이름은 2-10자 사이여야 합니다.")
    val name: String,

    @field:Pattern(
        regexp = "^\\d{3}-\\d{4}-\\d{4}$",
        message = "전화번호는 010-1234-5678 형식이어야 합니다."
    )
    val phone: String,
)