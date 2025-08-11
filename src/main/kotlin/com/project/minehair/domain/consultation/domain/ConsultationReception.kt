package com.project.minehair.domain.consultation.domain

import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class ConsultationReception(
    val categoryId: Long,
    val name: String,
    val phone: String,
    val phoneHash: String,
    val receptionState: ReceptionState,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    fun withPhoneData(encryptedPhone: String, phoneHash: String): ConsultationReception {
        return this.copy(
            phone = encryptedPhone,
            phoneHash = phoneHash
        )
    }

}