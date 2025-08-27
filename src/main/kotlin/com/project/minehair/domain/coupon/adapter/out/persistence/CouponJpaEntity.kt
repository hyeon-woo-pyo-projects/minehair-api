package com.project.minehair.domain.coupon.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "coupon")
class CouponJpaEntity(

    @Column(name = "content")
    val content: String,

    @Column(name = "condition_type")
    val conditionType: String,

    @Column(name = "discount_type")
    val discountType: String,

    @Column(name = "discount_amount")
    val discountAmount: BigDecimal,

    @Column(name = "period_start")
    val periodStart: LocalDate,

    @Column(name = "period_end")
    val periodEnd: LocalDate,

    @Column(name = "is_post")
    val isPost: Boolean,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}