package com.project.minehair.domain.consultation.domain

import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class ConsultationCategory(
    val name: String,
    val code: String,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 1L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 1L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    // 삭제 메서드 (소프트 delete)
    fun delete(): ConsultationCategory {
        return copy(
            status = Status.deleted,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }

}