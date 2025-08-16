package com.project.minehair.domain.consultation.domain

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationCategoryRequest
import com.project.minehair.domain.consultation.adapter.out.persistence.ConsultationCategoryJpaEntity
import org.springframework.stereotype.Component

@Component
class ConsultationCategoryMapper {

    fun toDomain(entity: ConsultationCategoryJpaEntity): ConsultationCategory {
        return ConsultationCategory(
            id = entity.id,
            code = entity.code,
            name = entity.name,
        )
    }

    fun toDomain(request: CreateConsultationCategoryRequest): ConsultationCategory {
        return ConsultationCategory(
            code = request.code,
            name = request.name,
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
            status = category.status,
            updatedAt = category.updatedAt,
            updatedId = category.updatedId,
        )
    }

    fun toResponse(category: ConsultationCategory): ConsultationCategoryResponse {
        return ConsultationCategoryResponse(
            id = category.id!!,
            code = category.code,
            name = category.name,
        )
    }

}