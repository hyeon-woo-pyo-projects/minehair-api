package com.project.minehair.domain.consultation.application.service

import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationCategoryResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.ConsultationReceptionResponse
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationCategoryRequest
import com.project.minehair.domain.consultation.adapter.`in`.web.dto.CreateConsultationReceptionRequest
import com.project.minehair.domain.consultation.application.port.`in`.ConsultationUseCase
import com.project.minehair.domain.consultation.application.port.out.ConsultationCategoryPersistencePort
import com.project.minehair.domain.consultation.application.port.out.ConsultationReceptionPersistencePort
import com.project.minehair.domain.consultation.domain.ConsultationCategoryMapper
import com.project.minehair.domain.consultation.domain.ConsultationReceptionMapper
import com.project.minehair.global.utils.CryptoUtil
import org.springframework.stereotype.Service

@Service
class ConsultationService(
    private val consultationCategoryPersistencePort: ConsultationCategoryPersistencePort,
    private val consultationCategoryMapper: ConsultationCategoryMapper,
    private val consultationReceptionPersistencePort: ConsultationReceptionPersistencePort,
    private val consultationReceptionMapper: ConsultationReceptionMapper,
    private val cryptoUtil: CryptoUtil
) : ConsultationUseCase {

    override fun getConsultationCategories(): List<ConsultationCategoryResponse> {
        return consultationCategoryPersistencePort.findAll()
            .map { consultationCategoryMapper.toResponse(it) }
    }

    override fun createConsultationCategory(request: CreateConsultationCategoryRequest): ConsultationCategoryResponse {
        val consultationCategory = consultationCategoryMapper.toDomain(request)
        val savedDomain = consultationCategoryPersistencePort.save(consultationCategory)
        return consultationCategoryMapper.toResponse(savedDomain)
    }

    override fun deleteConsultationCategory(id: Long): ConsultationCategoryResponse {
        //id로 조회 후 상태를 inactive로 변경
        val consultationCategory = consultationCategoryPersistencePort.findById(id)
        val consultationCategoryForDelete = consultationCategory.delete()
        val deletedConsultationCategory = consultationCategoryPersistencePort.save(consultationCategoryForDelete)
        return consultationCategoryMapper.toResponse(deletedConsultationCategory)
    }


    override fun createConsultationReception(request: CreateConsultationReceptionRequest): ConsultationReceptionResponse {

        val encryptedPhone = cryptoUtil.encrypt(request.phoneNumber)
        val phoneHash = cryptoUtil.hashForSearch(request.phoneNumber)

        val consultationReception = consultationReceptionMapper.toDomain(request)
            .withPhoneData(encryptedPhone, phoneHash)

        val savedDomain = consultationReceptionPersistencePort.saveConsultationReception(consultationReception)
        return consultationReceptionMapper.toResponse(savedDomain)
    }

    override fun getConsultationReceptionList(): List<ConsultationReceptionResponse> {
        return consultationReceptionPersistencePort.findAll()
            .map { consultationReceptionMapper.toResponse(it) }
    }
}