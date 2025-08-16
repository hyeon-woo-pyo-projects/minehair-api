package com.project.minehair.domain.consultation.adapter.`in`.web

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationReceptionResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationCategoryRequest
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationReceptionRequest
import com.project.minehair.domain.consultation.application.port.`in`.ConsultationUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
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
     * 상담 카테고리(관심시술) 생성
     */
    @Operation(summary = "상담 카테고리(관심시술) 생성", description = "상담 카테고리(관심시술) 생성")
    @PostMapping("/categories")
    fun createConsultationCategory(
        @Valid @RequestBody request: CreateConsultationCategoryRequest
    ): BaseResponse<ConsultationCategoryResponse> {
        return BaseResponse.success(consultationUseCase.createConsultationCategory(request))
    }

    /**
     * 상담 카테고리(관심시술) 삭제
     */
    @Operation(summary = "상담 카테고리(관심시술) 삭제", description = "상담 카테고리(관심시술) 삭제")
    @DeleteMapping("/categories/{id}")
    fun deleteConsultationCategory(@PathVariable id: Long): BaseResponse<ConsultationCategoryResponse> {
        return BaseResponse.success(consultationUseCase.deleteConsultationCategory(id))
    }


    /**
     * 상담 접수
     */
    @Operation(summary = "상담 접수", description = "상담 접수")
    @PostMapping("/reception")
    fun createConsultationReception(
        @Valid @RequestBody request: CreateConsultationReceptionRequest
    ): BaseResponse<ConsultationReceptionResponse> {
        return BaseResponse.success(consultationUseCase.createConsultationReception(request))
    }

    /**
     * 상담 신청 목록 조회
     */
    @Operation(summary = "상담 신청 목록 조회", description = "상담 신청 목록 조회")
    @GetMapping("/reception")
    fun getConsultationReceptionList(): BaseResponse<List<ConsultationReceptionResponse>> {
        return BaseResponse.success(consultationUseCase.getConsultationReceptionList())
    }

    /**
     * 상담 신청 처리
     */

}