package com.project.minehair.domain.coupon.application.service

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponRequest
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.UpdateCouponRequest
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

    override fun getPostedCouponList(): List<CouponResponse> {
        return couponPersistencePort.findAllPostedActiveStatus()
            .let {couponMapper.toResponseList(it)}
    }

    override fun getCouponDetails(id: Long): CouponResponse {
        return couponPersistencePort.findById(id)
            .let { couponMapper.toResponse(it) }
    }

    @Transactional
    override fun updateCoupon(id: Long, request: UpdateCouponRequest): CouponResponse {
        val coupon = couponPersistencePort.findById(id)
        val couponForUpdate = coupon.update(request)
        return couponMapper.toResponse(couponPersistencePort.save(couponForUpdate))
    }

    @Transactional
    override fun deleteCoupon(id: Long): CouponResponse {
        val coupon = couponPersistencePort.findById(id)
        val couponForDelete = coupon.delete()
        return couponMapper.toResponse(couponPersistencePort.save(couponForDelete))
    }

}