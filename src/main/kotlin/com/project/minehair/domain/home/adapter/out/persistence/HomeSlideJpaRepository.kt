package com.project.minehair.domain.home.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface HomeSlideJpaRepository: JpaRepository<HomeSlideJpaEntity, Long> {

    /**
     * 홈 슬라이드 조회
     */
    fun findAllByStatus(status: Status): List<HomeSlideJpaEntity>

}