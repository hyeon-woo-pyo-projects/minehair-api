package com.project.minehair.domain.user.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class UpdatePasswordRequest(

    @Schema(description = "새 비밀번호", example = "newPassword123!")
    @field:NotBlank(message = "비밀번호는 필수입니다.")
    @field:Size(min = 4, max = 20, message = "비밀번호는 4자 이상 20자 이하여야 합니다.")
    val newPassword: String,

    @Schema(description = "비밀번호 확인", example = "newPassword123!")
    @field:NotBlank(message = "비밀번호 확인은 필수입니다.")
    val confirmPassword: String
)
