package com.project.minehair.domain.logo.adapter.`in`.web.dto

import com.project.minehair.domain.logo.domain.LogoType
import io.swagger.v3.oas.annotations.media.Schema

data class LogoResponse(
    @Schema(name="id", description = "Id")
    val id: Long,
    @Schema(name="logoType", description = "로고 타입")
    val logoType: LogoType,
    @Schema(name="description", description = "상세 내용")
    val description: String?,
    @Schema(name="imageUrl", description = "이미지 url")
    val imageUrl: String,
)