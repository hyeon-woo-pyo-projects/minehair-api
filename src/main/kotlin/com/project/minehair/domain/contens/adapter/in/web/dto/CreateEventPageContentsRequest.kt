package com.project.minehair.domain.contens.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateEventPageContentsRequest(

    @Schema(description = "순서", example = "1", required = true)
    val orderNo: Long,

    @Schema(description = "슬라이드 순서", example = "1", required = true)
    @field:NotNull(message = "슬라이드 순서는 필수 입니다.")
    val slideOrderNo: Long,

    @Schema(description = "이미지 url", example = "/image/url", required = true)
    @field:NotBlank(message = "이미지 Url은 필수 입니다.")
    val imageUrl: String,

    @Schema(description = "링크 url", example = "/page/url", required = true)
    @field:NotBlank(message = "링크 Url은 필수 입니다.")
    val linkUrl: String,

    @Schema(description = "텍스트 내용", example = "이벤트 페이지 입니다.", required = false)
    val textContent: String? = null,

    @Schema(description = "포스트 추가 여부", example = "true", required = true)
    @field:NotNull(message = "포스트 추가 여부는 필수 입니다.")
    val isAddPost: Boolean

)
