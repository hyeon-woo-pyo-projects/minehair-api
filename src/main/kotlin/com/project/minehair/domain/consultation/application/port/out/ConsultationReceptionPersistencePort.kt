package com.project.minehair.domain.consultation.application.port.out

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.domain.ConsultationReception

interface ConsultationReceptionPersistencePort {

    /**
     * 상담 접수 저장
     */
    fun saveConsultationReception(consultationReception: ConsultationReception): ConsultationReception
}