package com.project.minehair.domain.coupon.application.service

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponRequest
import com.project.minehair.domain.coupon.application.port.`in`.CouponUseCase
import com.project.minehair.domain.coupon.application.port.out.CouponPersistencePort
import com.project.minehair.domain.coupon.domain.CouponMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class CouponService (
    private val couponPersistencePort: CouponPersistencePort,
    private val couponMapper: CouponMapper
): CouponUseCase {

    @Transactional
    override fun createCoupon(request: CreateCouponRequest): CouponResponse {
        val couponForCreate = couponMapper.toDomain(request)
        return couponMapper.toResponse(couponPersistencePort.save(couponForCreate))
    }

    override fun getCouponList(): List<CouponResponse> {
        return couponPersistencePort.findAllActiveStatus()
            .let {couponMapper.toResponseList(it)}
    }

}