package com.project.minehair.domain.coupon.adapter.`in`.web.dto

import java.time.LocalDate

data class CouponIssueResponse(
    val id: Long,
    val couponId: Long,
    val usersId: Long,
    val issueDate: LocalDate,
    val isUse: Boolean,
    val useDate: LocalDate?,
) {

}
