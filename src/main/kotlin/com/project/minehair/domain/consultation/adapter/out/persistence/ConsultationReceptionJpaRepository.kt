package com.project.minehair.domain.consultation.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConsultationReceptionJpaRepository : JpaRepository<ConsultationReceptionJpaEntity, Long> {
}