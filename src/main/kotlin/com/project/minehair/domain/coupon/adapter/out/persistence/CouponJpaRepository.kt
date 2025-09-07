package com.project.minehair.domain.coupon.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponJpaRepository : JpaRepository<CouponJpaEntity, Long> {

    /**
     * 활성상태 쿠폰 목록 조회
     */
    fun findAllByStatus(status: Status): List<CouponJpaEntity>

    /**
     * 게시된 쿠폰 활성 리스트 조회
     */
    fun findAllByStatusAndIsPost(status: Status, isPost: Boolean): List<CouponJpaEntity>

    /**
     * id Set으로 쿠폰 조회
     */
    fun findAllByIdIn(idSet: Set<Long>): List<CouponJpaEntity>
}