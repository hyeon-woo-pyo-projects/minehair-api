package com.project.minehair.domain.coupon.adapter.`in`.web

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponIssueResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponIssueRequest
import com.project.minehair.domain.coupon.application.port.`in`.CouponIssueUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "쿠폰 API", description = "쿠폰 API")
@RestController
@RequestMapping("/api/coupon/issue")
class CouponIssueInboundWebAdapter(
    private val couponIssueUseCase: CouponIssueUseCase
) {

    @Operation(summary = "쿠폰 발급", description = "쿠폰 발급")
    @PostMapping
    fun issueCoupon(@Valid @RequestBody request: CreateCouponIssueRequest): BaseResponse<CouponIssueResponse> {
        return BaseResponse.success(couponIssueUseCase.issueCoupon(request))
    }

    @Operation(summary = "유저에게 발급된 쿠폰 조회", description = "유저에게 발급된 쿠폰 조회")
    @GetMapping
    fun getUserIssuedCoupons(): BaseResponse<List<CouponIssueResponse>> {
        return BaseResponse.success(couponIssueUseCase.getUserIssuedCoupons())
    }

    @Operation(summary = "쿠폰 사용", description = "쿠폰 사용")
    @PostMapping("/use/{id}")
    fun useCoupon(@PathVariable id: Long): BaseResponse<CouponIssueResponse> {
        return BaseResponse.success(couponIssueUseCase.useCoupon(id))
    }

}