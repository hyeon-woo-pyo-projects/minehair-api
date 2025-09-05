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
@Table(name = "coupon_issue")
class CouponIssueJpaEntity(

    @Column(name = "coupon_id")
    val couponId: Long,

    @Column(name = "user_id")
    val userId: Long,

    @Column(name = "issue_date")
    val issueDate: LocalDate,

    @Column(name = "is_use")
    val isUse: Boolean,

    @Column(name = "use_date")
    val useDate: LocalDate?,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}