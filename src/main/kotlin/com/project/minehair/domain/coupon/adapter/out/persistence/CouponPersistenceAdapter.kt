package com.project.minehair.domain.coupon.adapter.out.persistence

import com.project.minehair.domain.coupon.application.port.out.CouponPersistencePort
import com.project.minehair.domain.coupon.domain.Coupon
import com.project.minehair.domain.coupon.domain.CouponMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class CouponPersistenceAdapter(
    private val couponJpaRepository: CouponJpaRepository,
    private val couponMapper: CouponMapper
): CouponPersistencePort {

    override fun save(domain: Coupon): Coupon {
        val entityForCreate = couponMapper.toEntity(domain)
        return couponMapper.toDomain(couponJpaRepository.save(entityForCreate))
    }

    override fun findAllActiveStatus(): List<Coupon> {
        return couponJpaRepository.findAllByStatus(Status.active)
            .let { couponMapper.toDomainList(it) }
    }

    override fun findAllPostedActiveStatus(): List<Coupon> {
        return couponJpaRepository.findAllByStatusAndIsPost(Status.active, true)
            .let { couponMapper.toDomainList(it) }
    }

    override fun findById(id: Long): Coupon {
        return couponJpaRepository.findById(id).get()
            .let { couponMapper.toDomain(it) }
    }

}