package com.project.minehair.domain.consultation.application.port.out

import com.project.minehair.domain.consultation.domain.ConsultationCategory

interface ConsultationCategoryPersistencePort {

    /**
     * 상담 카테고리(관심시술) 목록 조회
     */
    fun findAll(): List<ConsultationCategory>

    /**
     * 상담 카테고리(관심시술) 저장
     */
    fun save(consultationCategory: ConsultationCategory): ConsultationCategory

    /**
     * 상담 카테고리(관심시술) 상세 조회
     */
     fun findById(id: Long): ConsultationCategory

}