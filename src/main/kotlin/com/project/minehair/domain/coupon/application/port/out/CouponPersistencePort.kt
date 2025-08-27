package com.project.minehair.domain.coupon.application.port.out

import com.project.minehair.domain.coupon.domain.Coupon

interface CouponPersistencePort {

    /**
     * 쿠폰 생성
     */
    fun save(domain: Coupon): Coupon

    /**
     * 쿠폰 리스트 조회
     */
    fun findAllActiveStatus(): List<Coupon>

}