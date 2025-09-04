package com.project.minehair.domain.contents.domain

import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdateEventPageContentsRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class EventPageContents(

    val orderNo: Int,
    val slideOrderNo: Int,
    val imageUrl: String,
    val linkUrl: String,
    val textContent: String? = null,
    val isAddPost: Boolean,

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

    fun updateOrderNo(newOrderNo: Int): EventPageContents {
        return copy(orderNo = newOrderNo, updatedId = 1L, updatedAt = LocalDateTime.now())
    }

    fun updateSlideOrderNo(newSlideOrderNo: Int): EventPageContents {
        return copy(slideOrderNo = newSlideOrderNo, updatedId = 1L, updatedAt = LocalDateTime.now())
    }

    fun updateFrom(updateRequest: UpdateEventPageContentsRequest) = copy(
        orderNo = updateRequest.orderNo,
        slideOrderNo = updateRequest.slideOrderNo,
        imageUrl = updateRequest.imageUrl,
        linkUrl = updateRequest.linkUrl,
        textContent = updateRequest.textContent,
        isAddPost = updateRequest.isAddPost,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )
}