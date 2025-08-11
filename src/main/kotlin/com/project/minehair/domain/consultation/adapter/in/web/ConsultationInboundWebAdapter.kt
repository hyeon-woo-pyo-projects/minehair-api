package com.project.minehair.domain.consultation.adapter.`in`.web

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationReceptionResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationReceptionRequest
import com.project.minehair.domain.consultation.application.port.`in`.ConsultationUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "상담 API", description = "상담 API")
@RestController
@RequestMapping("/api/consultation")
class ConsultationInboundWebAdapter(
    private val consultationUseCase: ConsultationUseCase
) {

    /**
     * 상담 카테고리(관심시술) 조회
     */
    @Operation(summary = "상담 카테고리(관심시술) 조회", description = "상담 카테고리(관심시술) 조회")
    @GetMapping("/categories")
    fun getConsultationCategories(): BaseResponse<List<ConsultationCategoryResponse>> {
        return BaseResponse.success(consultationUseCase.getConsultationCategories())
    }

    /**
     * 상담 접수
     */
    @Operation(summary = "상담 접수", description = "상담 접수")
    @PostMapping("/reception")
    fun createConsultationReception(
        request: CreateConsultationReceptionRequest
    ): BaseResponse<ConsultationReceptionResponse> {
        return BaseResponse.success(consultationUseCase.createConsultationReception(request))
    }

    /**
     * 상담 신청 목록 조회
     */

    /**
     * 상담 신청 처리
     */

}