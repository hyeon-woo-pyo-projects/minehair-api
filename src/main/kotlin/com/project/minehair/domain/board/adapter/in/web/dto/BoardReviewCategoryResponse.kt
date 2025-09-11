package com.project.minehair.domain.board.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class BoardReviewCategoryResponse(

    @Schema(description = "카테고리 ID", example = "1")
    val id: Long,
    @Schema(description = "카테고리 이름", example = "일반")
    val name: String,
    @Schema(description = "카테고리 순서", example = "1")
    val orderNo: Int,

)
