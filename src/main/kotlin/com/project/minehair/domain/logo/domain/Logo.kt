package com.project.minehair.domain.logo.domain

import com.project.minehair.domain.logo.adapter.`in`.web.dto.UpdateLogoRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class Logo(

    val logoType: LogoType,
    val description: String?,
    val imageUrl: String,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    fun delete() = copy(
        status = Status.deleted,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )

    fun updateFrom(updateRequest: UpdateLogoRequest) = copy(
        logoType = updateRequest.logoType,
        description = updateRequest.description,
        imageUrl = updateRequest.imageUrl,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )
}