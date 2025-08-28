package com.project.minehair.domain.coupon.adapter.`in`.web

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponRequest
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.UpdateCouponRequest
import com.project.minehair.domain.coupon.application.port.`in`.CouponUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "쿠폰 API", description = "쿠폰 API")
@RestController
@RequestMapping("/api/coupon")
class CouponInboundWebAdapter(
    private val couponUseCase: CouponUseCase
) {

    @Operation(summary = "쿠폰 생성", description = "쿠폰 생성")
    @PostMapping
    fun createCoupon(@Valid @RequestBody createCouponRequest: CreateCouponRequest): BaseResponse<CouponResponse> {
        return BaseResponse.success(couponUseCase.createCoupon(createCouponRequest))
    }

    @Operation(summary = "쿠폰 리스트 조회", description = "쿠폰 리스트 조회")
    @GetMapping
    fun getCouponList(): BaseResponse<List<CouponResponse>> {
        return BaseResponse.success(couponUseCase.getCouponList())
    }

    @Operation(summary = "게시된 쿠폰 리스트 조회", description = "게시된 쿠폰 리스트 조회")
    @GetMapping("/posted")
    fun getPostedCouponList(): BaseResponse<List<CouponResponse>> {
        return BaseResponse.success(couponUseCase.getPostedCouponList())
    }

    @Operation(summary = "쿠폰 상세 조회", description = "쿠폰 상세 조회")
    @GetMapping("/{id}")
    fun getCouponDetails(@PathVariable id: Long): BaseResponse<CouponResponse> {
        return BaseResponse.success(couponUseCase.getCouponDetails(id))
    }

    @Operation(summary = "쿠폰 수정", description = "쿠폰 수정")
    @PatchMapping("/{id}")
    fun updateCoupon(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateCouponRequest
    ): BaseResponse<CouponResponse> {
        return BaseResponse.success(couponUseCase.updateCoupon(id, request))
    }

    @Operation(summary = "쿠폰 삭제", description = "쿠폰 삭제")
    @DeleteMapping("/{id}")
    fun deleteCoupon(@PathVariable id: Long): BaseResponse<CouponResponse> {
        return BaseResponse.success(couponUseCase.deleteCoupon(id))
    }

}