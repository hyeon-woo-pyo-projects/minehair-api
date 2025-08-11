package com.project.minehair.domain.consultation.application.port.out

import com.project.minehair.domain.consultation.domain.ConsultationCategory

interface ConsultationCategoryPersistencePort {

    /**
     * 상담 카테고리(관심시술) 조회
     */
    fun findAll(): List<ConsultationCategory>

}