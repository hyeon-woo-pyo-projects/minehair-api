package com.project.minehair.domain.user.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class UserDetailsRequest(
    @Schema(description = "사용자 아이디", example = "user123", required = true)
    @field:NotBlank(message = "사용자 아이디는 필수입니다.")
    val userId: String,

    @Schema(description = "사용자 이름", example = "홍길동", required = true)
    @field:NotBlank(message = "사용자 이름은 필수입니다.")
    val name: String,

    @Schema(description = "휴대폰 번호", example = "01012345678", required = true)
    @field:NotBlank(message = "휴대폰 번호는 필수입니다.")
    val phone: String,
)
