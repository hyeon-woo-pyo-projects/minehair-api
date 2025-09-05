package com.project.minehair.domain.coupon.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class CreateCouponIssueRequest(

    @Schema(description = "쿠폰 ID", example = "1", required = true)
    @NotNull(message = "couponId는 필수 입니다.")
    val couponId: Long,

)


