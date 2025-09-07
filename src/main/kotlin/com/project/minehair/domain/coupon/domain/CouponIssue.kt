package com.project.minehair.domain.coupon.domain

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDate
import java.time.LocalDateTime

data class CouponIssue(

    val couponId: Long,
    val userId: Long,
    val issueDate: LocalDate,
    val isUse: Boolean,
    val useDate: LocalDate? = null,
    val couponInfo: CouponResponse? = null,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    companion object {
        fun of(
            couponId: Long,
            usersId: Long,
            issueDate: LocalDate = LocalDate.now(),
            isUse: Boolean = false
        ): CouponIssue {
            return CouponIssue(
                couponId = couponId,
                userId = usersId,
                issueDate = issueDate,
                isUse = isUse,
            )
        }
    }

    fun use (
    ): CouponIssue {
        return copy(
            isUse = true,
            updatedAt = LocalDateTime.now(),
            useDate = LocalDate.now()
        )
    }

    fun mapCoupon(couponResponse: CouponResponse) : CouponIssue {
        return this.copy(
            couponInfo = couponResponse
        )
    }


}