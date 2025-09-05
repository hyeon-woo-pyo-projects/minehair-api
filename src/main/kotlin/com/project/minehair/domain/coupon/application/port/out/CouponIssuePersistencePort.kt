package com.project.minehair.domain.coupon.application.port.out

import com.project.minehair.domain.coupon.domain.CouponIssue

interface CouponIssuePersistencePort {

    /**
     * 쿠폰 아이디와 유저 아이디로 특정 쿠폰 조회
     */
    fun findByCouponIdAndUserId(couponId: Long, userId: Long): CouponIssue?

    /**
     * 쿠폰 생성
     */
    fun save(domain: CouponIssue): CouponIssue

    /**
     * 특정 유저가 발급받은 쿠폰 조회
     */
    fun findAllByUserIdActiveStatus(userId: Long): List<CouponIssue>

    /**
     * id로 특정 쿠폰 조회
     */
    fun findById(id: Long): CouponIssue?

}