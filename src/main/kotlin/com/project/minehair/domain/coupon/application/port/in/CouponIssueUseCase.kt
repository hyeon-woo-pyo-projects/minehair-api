package com.project.minehair.domain.coupon.application.port.`in`

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponIssueResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponIssueRequest

interface CouponIssueUseCase {

    fun issueCoupon(request: CreateCouponIssueRequest): CouponIssueResponse

    fun getUserIssuedCoupons(): List<CouponIssueResponse>

    fun useCoupon(id: Long): CouponIssueResponse

}