package com.project.minehair.domain.contens.domain

import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateSnsPlatformRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class SnsPlatform(

    val orderNo: Int,
    val imageUrl: String,
    val linkUrl: String,

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

    fun updateOrderNo(newOrderNo: Int): SnsPlatform {
        return copy(orderNo = newOrderNo, updatedId = 1L, updatedAt = LocalDateTime.now())
    }

    fun updateFrom(updateRequest: UpdateSnsPlatformRequest) = copy(
        orderNo = updateRequest.orderNo,
        imageUrl = updateRequest.imageUrl,
        linkUrl = updateRequest.linkUrl,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )
}