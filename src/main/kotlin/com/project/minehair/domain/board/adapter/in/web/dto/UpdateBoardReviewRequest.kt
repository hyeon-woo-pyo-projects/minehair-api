package com.project.minehair.domain.board.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateBoardReviewRequest(
    @Schema(description = "카테고리 ID", example = "1", required = true)
    val categoryId: Long,
    @Schema(description = "제목", example = "리뷰 제목", required = true)
    val title: String,
    @Schema(description = "내용", example = "리뷰 내용", required = false)
    val content: String?,
    @Schema(description = "이미지 URL", example = "http://example.com/image.jpg", required = false)
    val imageUrl: String?
)
