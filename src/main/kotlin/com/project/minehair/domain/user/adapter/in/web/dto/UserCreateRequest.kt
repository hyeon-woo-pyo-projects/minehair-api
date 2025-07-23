package com.project.minehair.domain.user.adapter.`in`.web.dto

import com.project.minehair.domain.user.domain.UserType
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size

// 회원가입 요청 DTO
data class UserCreateRequest(
    @field:NotBlank(message = "사용자 ID는 필수입니다.")
    @field:Size(min = 4, max = 20, message = "사용자 ID는 4-20자 사이여야 합니다.")
    @field:Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "사용자 ID는 영문, 숫자, 언더스코어만 사용 가능합니다.")
    val userId: String,

    @field:NotBlank(message = "이메일은 필수입니다.")
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    val email: String,

    @field:NotBlank(message = "비밀번호는 필수입니다.")
    @field:Size(min = 8, max = 20, message = "비밀번호는 8-20자 사이여야 합니다.")
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]+$",
        message = "비밀번호는 영문, 숫자, 특수문자를 포함해야 합니다."
    )
    val password: String,

    @field:NotBlank(message = "이름은 필수입니다.")
    @field:Size(min = 2, max = 10, message = "이름은 2-10자 사이여야 합니다.")
    val name: String,

    @field:Pattern(
        regexp = "^\\d{3}-\\d{4}-\\d{4}$",
        message = "전화번호는 010-1234-5678 형식이어야 합니다."
    )
    val phone: String,

    val userType: UserType = UserType.member
)