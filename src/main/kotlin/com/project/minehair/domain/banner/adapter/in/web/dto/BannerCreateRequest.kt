package com.project.minehair.domain.banner.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

@Schema(description = "배너 생성 정보")
data class BannerCreateRequest(
    @Schema(description = "배너 내용", example = "새로운 배너를 추가해보세요.", required = true)
    @field:NotBlank(message = "배너 내용은 필수입니다.")
    val content: String,

    @Schema(description = "배너 컬러", example = "#FF5733", required = true)
    @field:NotBlank(message = "배너 컬러는 필수입니다.")
    val color: String,

    @Schema(description = "배너 링크", example = "http://example.com", required = true)
    @field:NotBlank(message = "배너 링크는 필수입니다.")
    val link: String

)
