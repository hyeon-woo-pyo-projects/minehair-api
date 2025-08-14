package com.project.minehair.domain.consultation.application.port.`in`

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationReceptionResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationReceptionRequest

interface ConsultationUseCase {

    /**
     * 상담 카테고리(관심시술) 조회
     */
    fun getConsultationCategories(): List<ConsultationCategoryResponse>

    /**
     * 상담 접수
     */
    fun createConsultationReception(request: CreateConsultationReceptionRequest): ConsultationReceptionResponse

    /**
     * 상담 신청 목록 조회
     */
    fun getConsultationReceptionList(): List<ConsultationReceptionResponse>
//
//    /**
//     * 상담 신청 처리
//     */
//    fun processConsultationApplication(id: Long, request: ConsultationProcessRequest)

}