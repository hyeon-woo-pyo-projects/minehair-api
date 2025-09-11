package com.project.minehair.domain.board.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class CreateBoardReviewCategoryRequest(

    @Schema(description = "카테고리 이름", example = "일반", required = true)
    @field:NotBlank(message = "카테고리 이름은 필수 입니다.")
    val name: String,

)
