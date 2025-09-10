package com.project.minehair.domain.contents.domain

import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdatePageContentsRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class PageContents(

    val menuId: Long,
    val pageUrl: String,
    val contentsType: ContentsType,
    val contentsUrl: String,
    val orderNo: Int,
    val videoBackGroundUrl: String?,
    val consultingBackGroundUrl: String?,

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

    fun updateOrderNo(newOrderNo: Int): PageContents {
        return copy(orderNo = newOrderNo, updatedId = 1L, updatedAt = LocalDateTime.now())
    }

    fun updateFrom(updateRequest: UpdatePageContentsRequest) = copy(
        menuId = updateRequest.menuId,
        contentsType = updateRequest.contentsType,
        contentsUrl = updateRequest.contentsUrl,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )
}