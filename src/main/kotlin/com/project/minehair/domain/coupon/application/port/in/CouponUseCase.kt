package com.project.minehair.domain.coupon.application.port.`in`

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponRequest

interface CouponUseCase {

    /**
     * 쿠폰 등록
     */
    fun createCoupon(request: CreateCouponRequest): CouponResponse

    /**
     * 쿠폰 목록 조회
     */
    fun getCouponList(): List<CouponResponse>

}