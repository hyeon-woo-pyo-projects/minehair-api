package com.project.minehair.domain.coupon.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import java.math.BigDecimal
import java.time.LocalDate

data class UpdateCouponRequest(

    @Schema(description = "쿠폰 내용", example = "테스트 쿠폰", required = true)
    @NotBlank
    val content: String,

    @Schema(description = "조건 타입(구입액, 생일 등)", example = "PURCHASE", required = true)
    @NotBlank
    val conditionType: String,

    @Schema(description = "할인 타입(정액, 정률)", example = "PRICE", required = true)
    @NotBlank
    val discountType: String,

    @Schema(description = "할인액 (정액, 정률)", example = "10000", required = true)
    @NotEmpty
    val discountAmount: BigDecimal,

    @Schema(description = "시작 기간", example = "2025-08-01", required = true)
    @NotEmpty
    val periodStart: LocalDate,

    @Schema(description = "종료 기간", example = "2025-08-30", required = true)
    @NotEmpty
    val periodEnd: LocalDate,

    @Schema(description = "게시 여부", example = "true", required = true)
    @NotEmpty
    val isPost: Boolean,

) {

}
