package com.project.minehair.domain.consultation.domain

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationReceptionResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationReceptionRequest
import com.project.minehair.domain.consultation.adapter.out.persistence.ConsultationReceptionJpaEntity
import org.springframework.stereotype.Component

@Component
class ConsultationReceptionMapper {

    fun toDomain(entity: ConsultationReceptionJpaEntity): ConsultationReception {
        return ConsultationReception(
            id = entity.id,
            categoryId = entity.categoryId,
            name = entity.name,
            phone = entity.phone,
            phoneHash = entity.phoneHash,
            receptionState = entity.receptionState,
        )
    }

    fun toDomain(request: CreateConsultationReceptionRequest): ConsultationReception {
        return ConsultationReception(
            categoryId = request.consultationCategoryId,
            name = request.name,
            phone = request.phoneNumber,
            phoneHash = "",
            receptionState = ReceptionState.PENDING // Default state when creating a new reception
        )
    }

    fun toEntity(reception: ConsultationReception): ConsultationReceptionJpaEntity {
        return ConsultationReceptionJpaEntity(
            id = reception.id,
            categoryId = reception.categoryId,
            name = reception.name,
            phone = reception.phone,
            phoneHash = reception.phoneHash,
            receptionState = reception.receptionState,
        )
    }

    fun toResponse(reception: ConsultationReception): ConsultationReceptionResponse {
        return ConsultationReceptionResponse(
            id = reception.id!!,
            name = reception.name,
        )
    }

}