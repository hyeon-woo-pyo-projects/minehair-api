package com.project.minehair.domain.coupon.domain

import com.project.minehair.domain.coupon.adapter.`in`.web.dto.CouponIssueResponse
import com.project.minehair.domain.coupon.adapter.out.persistence.CouponIssueJpaEntity
import org.springframework.stereotype.Component

@Component
class CouponIssueMapper {

    fun toDomain(entity: CouponIssueJpaEntity): CouponIssue {
        return CouponIssue(
            id = entity.id,
            couponId = entity.couponId,
            userId = entity.userId,
            issueDate = entity.issueDate,
            isUse = entity.isUse,
            useDate = entity.useDate,
        )
    }

    fun toDomainList(entityList: List<CouponIssueJpaEntity>): List<CouponIssue> {
        return entityList.map { toDomain(it) }
    }

    fun toResponse(domain: CouponIssue): CouponIssueResponse {
        return CouponIssueResponse(
            id = domain.id!!,
            couponId = domain.couponId,
            usersId = domain.userId,
            issueDate = domain.issueDate,
            isUse = domain.isUse,
            useDate = domain.useDate,
            couponInfo = domain.couponInfo
        )
    }

    fun toResponseList(domainList: List<CouponIssue>): List<CouponIssueResponse> {
        return domainList.map { toResponse(it) }
    }

    fun toEntity(domain: CouponIssue): CouponIssueJpaEntity {
        return CouponIssueJpaEntity(
            id = domain.id,
            couponId = domain.couponId,
            userId = domain.userId,
            issueDate = domain.issueDate,
            isUse = domain.isUse,
            useDate = domain.useDate,
            status = domain.status,
            createdAt = domain.createdAt,
            updatedAt = domain.updatedAt
        )
    }

}