package com.project.minehair.domain.contents.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdateSnsPlatformRequest(

    @Schema(description = "로고 ID", example = "1", required = true)
    @field:NotNull(message = "로고 ID는 필수 입니다.")
    val logoId: Long,

    @Schema(description = "순서", example = "1", required = true)
    @field:NotNull(message = "순서는 필수 입니다.")
    val orderNo: Int,

    @Schema(description = "이미지 url", example = "/image/url", required = true)
    @field:NotBlank(message = "이미지 Url은 필수 입니다.")
    val imageUrl: String,

    @Schema(description = "링크 url", example = "/page/url", required = true)
    @field:NotBlank(message = "링크 Url은 필수 입니다.")
    val linkUrl: String,

)
