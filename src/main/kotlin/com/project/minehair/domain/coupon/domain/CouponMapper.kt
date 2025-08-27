package com.project.minehair.domain.coupon.domain

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponResponse
import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CreateCouponRequest
import com.project.minehair.domain.coupon.adapter.out.persistence.CouponJpaEntity
import org.springframework.stereotype.Component

@Component
class CouponMapper {

    fun toDomain(entity: CouponJpaEntity): Coupon {
        return Coupon(
            id = entity.id,
            content = entity.content,
            conditionType = entity.conditionType,
            discountType = entity.discountType,
            discountAmount = entity.discountAmount,
            periodStart = entity.periodStart,
            periodEnd = entity.periodEnd,
            isPost = entity.isPost,
        )
    }

    fun toDomainList(entityList: List<CouponJpaEntity>): List<Coupon> {
        return entityList.map { toDomain(it) }
    }

    fun toDomain(request: CreateCouponRequest): Coupon {
        return Coupon(
            content = request.content,
            conditionType = request.conditionType,
            discountType = request.discountType,
            discountAmount = request.discountAmount,
            periodStart = request.periodStart,
            periodEnd = request.periodEnd,
            isPost = request.isPost,
        )
    }

    fun toResponse(domain: Coupon): CouponResponse {
        return CouponResponse(
            id = domain.id!!,
            content = domain.content,
            conditionType = domain.conditionType,
            discountType = domain.discountType,
            discountAmount = domain.discountAmount,
            periodStart = domain.periodStart,
            periodEnd = domain.periodEnd,
            isPost = domain.isPost,
        )
    }

    fun toResponseList(domainList: List<Coupon>): List<CouponResponse> {
        return domainList.map { toResponse(it) }
    }

    fun toEntity(domain: Coupon): CouponJpaEntity {
        return CouponJpaEntity(
            id = domain.id,
            content = domain.content,
            conditionType = domain.conditionType,
            discountType = domain.discountType,
            discountAmount = domain.discountAmount,
            periodStart = domain.periodStart,
            periodEnd = domain.periodEnd,
            isPost = domain.isPost,
            status = domain.status,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }

}