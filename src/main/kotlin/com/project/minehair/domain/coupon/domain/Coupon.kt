package com.project.minehair.domain.coupon.domain

import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

class Coupon(

    val content: String,
    val conditionType: String,
    val discountType: String,
    val discountAmount: BigDecimal,
    val periodStart: LocalDate,
    val periodEnd: LocalDate,
    val isPost: Boolean,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {
}