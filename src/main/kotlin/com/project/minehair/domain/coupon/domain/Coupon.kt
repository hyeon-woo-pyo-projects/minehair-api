package com.project.minehair.domain.coupon.domain

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.UpdateCouponRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

data class Coupon(

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

    fun update(
        request: UpdateCouponRequest
    ): Coupon {
        return this.copy(
            content = request.content,
            conditionType = request.conditionType,
            discountType = request.discountType,
            discountAmount = request.discountAmount,
            periodStart = request.periodStart,
            periodEnd = request.periodEnd,
            isPost = request.isPost,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }

    fun delete(): Coupon {
        return this.copy(
            status = Status.deleted,
            updatedAt = LocalDateTime.now()
        )
    }

    // 기간이 지났는지 확인
    fun isExpired(): Boolean {
        return periodEnd.isBefore(LocalDate.now())
    }

}