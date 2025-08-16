package com.project.minehair.domain.consultation.adapter.out.persistence

import com.project.minehair.domain.consultation.application.port.out.ConsultationCategoryPersistencePort
import com.project.minehair.domain.consultation.domain.ConsultationCategory
import com.project.minehair.domain.consultation.domain.ConsultationCategoryMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class ConsultationCategoryPersistenceAdapter(
    private val consultationCategoryJpaRepository: ConsultationCategoryJpaRepository,
    private val consultationCategoryMapper: ConsultationCategoryMapper
) : ConsultationCategoryPersistencePort {

    override fun findAll(): List<ConsultationCategory> {
        return consultationCategoryJpaRepository.findAllByStatus(Status.active)
            .let { consultationCategoryMapper.toDomainList(it) }
    }

    override fun save(consultationCategory: ConsultationCategory): ConsultationCategory {
        val entity = consultationCategoryMapper.toEntity(consultationCategory)
        return consultationCategoryJpaRepository.save(entity)
            .let { consultationCategoryMapper.toDomain(it) }
    }

    override fun findById(id: Long): ConsultationCategory {
        return consultationCategoryJpaRepository.findById(id)
            .orElseThrow { IllegalArgumentException("Consultation Category not found with id: $id") }
            .let { consultationCategoryMapper.toDomain(it) }
    }

}