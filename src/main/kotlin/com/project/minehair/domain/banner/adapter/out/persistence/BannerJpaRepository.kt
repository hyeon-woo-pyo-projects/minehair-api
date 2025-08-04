package com.project.minehair.domain.banner.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BannerJpaRepository : JpaRepository<BannerJpaEntity, Long> {

    fun findByIsPostAndStatus(isPost: Boolean, status: Status): BannerJpaEntity?

    fun findAllByStatus(status: Status): List<BannerJpaEntity>

    fun findByIdAndStatus(id: Long, status: Status): BannerJpaEntity?

}