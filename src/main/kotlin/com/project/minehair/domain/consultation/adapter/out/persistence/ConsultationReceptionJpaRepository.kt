package com.project.minehair.domain.consultation.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConsultationReceptionJpaRepository : JpaRepository<ConsultationReceptionJpaEntity, Long> {

    /**
     * 상태가 active인 상담 접수 목록 조회
     */
    fun findAllByStatus(status: Status): List<ConsultationReceptionJpaEntity>

}