package com.project.minehair.domain.consultation.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank

data class CreateConsultationCategoryRequest(

    @Schema(name = "code", description = "상담 카테고리 코드", example = "root_bleaching")
    @field:NotBlank(message = "상담 카테고리 코드는 필수입니다.")
    val code: String,

    @Schema(name = "name", description = "상담 카테고리 이름", example = "뿌리 탈색")
    @field:NotBlank(message = "상담 카테고리 이름은 필수입니다.")
    val name: String,

)
