package com.project.minehair.domain.consultation.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConsultationCategoryJpaRepository: JpaRepository<ConsultationCategoryJpaEntity, Long> {

    /**
     * 상담 카테고리(관심시술) 조회
     */
    fun findAllByStatus(status: Status): List<ConsultationCategoryJpaEntity>
}