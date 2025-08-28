package com.project.minehair.domain.coupon.application.port.`in`

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponRequest
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.UpdateCouponRequest

interface CouponUseCase {

    /**
     * 쿠폰 등록
     */
    fun createCoupon(request: CreateCouponRequest): CouponResponse

    /**
     * 쿠폰 목록 조회
     */
    fun getCouponList(): List<CouponResponse>

    /**
     * 게시된 쿠폰 목록 조회
     */
    fun getPostedCouponList(): List<CouponResponse>

    /**
     * 쿠폰 상세 조회
     */
    fun getCouponDetails(id: Long): CouponResponse

    /**
     * 쿠폰 수정
     */
    fun updateCoupon(id: Long, request: UpdateCouponRequest): CouponResponse

    /**
     * 쿠폰 삭제
     */
    fun deleteCoupon(id: Long): CouponResponse
}