package com.project.minehair.domain.user.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.*

// 회원가입 요청 DTO
@Schema(description = "회원가입 요청 정보")
data class UserCreateRequest(

    @Schema(description = "사용자 아이디", example = "user123", required = true)
    @field:NotBlank(message = "사용자 아이디는 필수입니다.")
    @field:Size(min = 4, max = 20, message = "사용자 아이디는 4자 이상 20자 이하여야 합니다.")
    @field:Pattern(
        regexp = "^[a-zA-Z0-9_]+$",
        message = "사용자 아이디는 영문, 숫자, 언더스코어만 사용 가능합니다."
    )
    val userId: String,

    @Schema(description = "비밀번호", example = "password123!", required = true)
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    @field:Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하여야 합니다.")
    val password: String,

    @Schema(description = "사용자 이름", example = "홍길동", required = true)
    @field:NotBlank(message = "사용자 이름은 필수입니다.")
    @field:Size(min = 2, max = 10, message = "사용자 이름은 2자 이상 10자 이하여야 합니다.")
    val name: String,

    @Schema(description = "휴대폰 번호", example = "01012345678", required = true)
    @field:NotBlank(message = "휴대폰 번호는 필수입니다.")
    @field:Pattern(
        regexp = "^01[0-9]{8,9}$",
        message = "올바른 휴대폰 번호 형식이 아닙니다. (예: 01012345678)"
    )
    val phone: String,

    @Schema(description = "이메일 주소", example = "user@example.com", required = true)
    @field:NotBlank(message = "이메일은 필수입니다.")
    @field:Email(message = "올바른 이메일 형식이 아닙니다.")
    @field:Size(max = 100, message = "이메일은 100자 이하여야 합니다.")
    val email: String,
)