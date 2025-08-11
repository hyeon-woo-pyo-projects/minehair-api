package com.project.minehair.domain.consultation.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class CreateConsultationReceptionRequest(

    @Schema(description = "이름", example = "홍길동", required = true)
    @field:NotBlank(message = "이름은 필수입니다.")
    val name: String,

    @Schema(description = "전화번호", example = "01012345678", required = true)
    @field:NotBlank(message = "전화번호는 필수입니다.")
    val phoneNumber: String,

    @Schema(description = "카테고리 ID", example = "1", required = true)
    @field:NotNull(message = "카테고리 ID는 필수입니다.")
    val consultationCategoryId: Long,

)
