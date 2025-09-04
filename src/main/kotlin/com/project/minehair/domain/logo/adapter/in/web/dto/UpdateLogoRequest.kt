package com.project.minehair.domain.logo.adapter.`in`.web.dto

import com.project.minehair.domain.logo.domain.LogoType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateLogoRequest(

    @Schema(description = "로고 타입", example = "MAIN", required = true)
    @field:NotNull(message = "로고 타입은 필수 입니다.")
    val logoType: LogoType,

    @Schema(description = "슬라이드 순서", example = "1", required = false)
    val description: String?,

    @Schema(description = "이미지 url", example = "/image/url", required = true)
    @field:NotBlank(message = "이미지 Url은 필수 입니다.")
    val imageUrl: String,
)
