package com.project.minehair.domain.banner.adapter.`in`.web.dto

import com.project.minehair.domain.banner.domain.BannerType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateBannerRequest(

    @Schema(description = "배너 내용", example = "MAIN, NAVIGATION.", required = true)
    @field:NotNull(message = "배너 타입은 필수 입니다.(MAIN, NAVIGATION)")
    val bannerType: BannerType,

    @Schema(description = "배너 내용", example = "새로운 배너를 추가해보세요.", required = false)
    val content: String?,

    @Schema(description = "배너 컬러", example = "#FF5733", required = false)
    val color: String?,

    @Schema(description = "배너 텍스트 컬러", example = "#FFFFFF", required = false)
    val textColor: String?,

    @Schema(description = "배너 링크", example = "http://example.com", required = true)
    @field:NotBlank(message = "배너 링크는 필수입니다.")
    val link: String,

    @Schema(description = "배너 이미지 URL", example = "http://example.com/image.png", required = true)
    @field:NotBlank(message = "배너 이미지 URL은 필수입니다.")
    val imageUrl: String,

    @Schema(description = "게시 여부", example = "true", required = true)
    @field:NotNull(message = "게시 여부는 필수입니다.")
    val isPost: Boolean
)
