package com.project.minehair.domain.coupon.adapter.out.persistence

import com.project.minehair.domain.coupon.application.port.out.CouponIssuePersistencePort
import com.project.minehair.domain.coupon.domain.CouponIssue
import com.project.minehair.domain.coupon.domain.CouponIssueMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class CouponIssuePersistenceAdapter(
    private val couponIssueJpaRepository: CouponIssueJpaRepository,
    private val couponIssueMapper: CouponIssueMapper
) : CouponIssuePersistencePort {

    override fun findByCouponIdAndUserId(couponId: Long, userId: Long): CouponIssue? {
        return couponIssueJpaRepository.findByCouponIdAndUserIdAndStatus(
            couponId,
            userId,
            Status.active
        )?.let { couponIssueMapper.toDomain(it) }
    }

    override fun save(domain: CouponIssue): CouponIssue {
        val entityForCreate = couponIssueMapper.toEntity(domain)
        return couponIssueMapper.toDomain(couponIssueJpaRepository.save(entityForCreate))
    }

    override fun findAllByUserIdActiveStatus(userId: Long): List<CouponIssue> {
        return couponIssueJpaRepository.findAllByUserIdAndIsUseAndStatus(
            userId,
            false,
            Status.active
        ).let { couponIssueMapper.toDomainList(it) }
    }

    override fun findById(id: Long): CouponIssue? {
        return couponIssueJpaRepository.findById(id).orElse(null)
            ?.let { couponIssueMapper.toDomain(it) }
    }

}