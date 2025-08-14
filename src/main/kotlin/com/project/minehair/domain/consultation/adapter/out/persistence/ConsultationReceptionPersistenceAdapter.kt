package com.project.minehair.domain.consultation.adapter.out.persistence

import com.project.minehair.domain.consultation.application.port.out.ConsultationReceptionPersistencePort
import com.project.minehair.domain.consultation.domain.ConsultationReception
import com.project.minehair.domain.consultation.domain.ConsultationReceptionMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class ConsultationReceptionPersistenceAdapter(
    private val consultationReceptionJpaRepository: ConsultationReceptionJpaRepository,
    private val consultationReceptionMapper: ConsultationReceptionMapper
): ConsultationReceptionPersistencePort {

    override fun saveConsultationReception(consultationReception: ConsultationReception): ConsultationReception {
        val savedEntity = consultationReceptionJpaRepository.save(
            consultationReceptionMapper.toEntity(consultationReception)
        )
        return consultationReceptionMapper.toDomain(savedEntity)
    }

    override fun findAll(): List<ConsultationReception> {
        return consultationReceptionJpaRepository.findAllByStatus(Status.active)
            .let { consultationReceptionMapper.toDomainList(it) }
    }

}