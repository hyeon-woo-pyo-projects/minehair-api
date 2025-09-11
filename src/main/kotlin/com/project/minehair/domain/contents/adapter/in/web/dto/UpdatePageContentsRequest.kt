package com.project.minehair.domain.contents.adapter.`in`.web.dto

import com.project.minehair.domain.contents.domain.ContentsType
import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class UpdatePageContentsRequest(

    @Schema(description = "메뉴 Id", example = "1", required = true)
    val menuId: Long = 0L,

    @Schema(description = "페이지 Url", example = "/url/test", required = true)
    val pageUrl: String?,

    @Schema(description = "컨텐츠 타입", example = "컨텐츠 타입", required = true)
    @field:NotNull(message = "컨텐츠 타입은 필수 입니다.")
    val contentsType: ContentsType,

    @Schema(description = "컨텐츠 url", example = "컨텐츠 url", required = true)
    @field:NotBlank(message = "컨텐츠 url은 필수 입니다.")
    val contentsUrl: String,

//    @Schema(description = "비디오 배경 Url", example = "/image/url", required = false)
//    val videoBackGroundUrl: String?,
//
//    @Schema(description = "컨설팅 배경 Url", example = "/image/url", required = false)
//    val consultingBackGroundUrl: String?,
)
