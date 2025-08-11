package com.project.minehair.domain.consultation.domain

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.adapter.out.persistence.ConsultationCategoryJpaEntity
import org.springframework.stereotype.Component

@Component
class ConsultationCategoryMapper {

    fun toDomain(entity: ConsultationCategoryJpaEntity): ConsultationCategory {
        return ConsultationCategory(
            id = entity.id,
            code = entity.code,
            name = entity.name,
            description = entity.description,
        )
    }

    fun toDomainList(entities: List<ConsultationCategoryJpaEntity>): List<ConsultationCategory> {
        return entities.map { toDomain(it) }
    }

    fun toEntity(category: ConsultationCategory): ConsultationCategoryJpaEntity {
        return ConsultationCategoryJpaEntity(
            id = category.id,
            code = category.code,
            name = category.name,
            description = category.description,
        )
    }

    fun toResponse(category: ConsultationCategory): ConsultationCategoryResponse {
        return ConsultationCategoryResponse(
            id = category.id!!,
            code = category.code,
            name = category.name,
            description = category.description
        )
    }

}