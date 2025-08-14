package com.project.minehair.domain.consultation.application.port.out

import com.project.minehair.domain.consultation.domain.ConsultationReception

interface ConsultationReceptionPersistencePort {

    /**
     * 상담 접수 저장
     */
    fun saveConsultationReception(consultationReception: ConsultationReception): ConsultationReception

    /**
     * 상담 접수 목록 조회
     */
    fun findAll(): List<ConsultationReception>
}