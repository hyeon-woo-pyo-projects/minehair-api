package com.project.minehair.domain.coupon.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponIssueJpaRepository : JpaRepository<CouponIssueJpaEntity, Long> {
    fun findByCouponIdAndUserIdAndStatus(couponId: Long, userId: Long, status: Status): CouponIssueJpaEntity?

    fun findAllByUserIdAndStatus(userId: Long, status: Status): List<CouponIssueJpaEntity>
}