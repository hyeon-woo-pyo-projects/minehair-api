package com.project.minehair.domain.banner.adapter.`in`.web.dto

import com.project.minehair.domain.banner.domain.BannerType
import io.swagger.v3.oas.annotations.media.Schema

data class BannerResponse(

    @Schema(name = "id", description = "배너 ID")
    val id: Long?,
    @Schema(name = "bannerType", description = "배너 타입")
    val bannerType: BannerType,
    @Schema(name = "content", description = "배너 내용")
    val content: String?,
    @Schema(name = "color", description = "배너 배경색")
    val color: String?,
    @Schema(name = "textColor", description = "배너 글자색")
    val textColor: String?,
    @Schema(name = "link", description = "배너 링크")
    val link: String,
    @Schema(name = "imageUrl", description = "배너 이미지 URL")
    val imageUrl: String?,
    @Schema(name = "isPost", description = "배너 게시 여부")
    val isPost: Boolean

)
