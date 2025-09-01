package com.project.minehair.domain.contens.adapter.`in`.web.dto

import com.project.minehair.domain.contens.domain.ContentsType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreatePageContentsRequest(

    @Schema(description = "메뉴 Id", example = "1", required = true)
    val menuId: Long = 0L,

    @Schema(description = "페이지 Url", example = "/url/test", required = true)
    @field:NotBlank(message = "페이지 Url은 필수 입니다.")
    val pageUrl: String,

    @Schema(description = "컨텐츠 타입", example = "IMAGE", required = true)
    @field:NotNull(message = "컨텐츠 타입은 필수 입니다.")
    val contentsType: ContentsType,

    @Schema(description = "컨텐츠 url", example = "/image/url", required = true)
    @field:NotBlank(message = "컨텐츠 Url은 필수 입니다.")
    val contentsUrl: String,
)
