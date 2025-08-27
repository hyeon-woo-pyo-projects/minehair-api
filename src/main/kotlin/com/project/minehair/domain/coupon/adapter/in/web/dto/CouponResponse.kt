package com.project.minehair.domain.coupon.adapter.`in`.web.dto

import java.math.BigDecimal
import java.time.LocalDate

data class CouponResponse(
    val id: Long,
    val content: String,
    val conditionType: String,
    val discountType: String,
    val discountAmount: BigDecimal,
    val periodStart: LocalDate,
    val periodEnd: LocalDate,
    val isPost: Boolean,
) {

}
