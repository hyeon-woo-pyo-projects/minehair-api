package com.project.minehair.domain.contens.domain

import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateMenuContentsRequest
import com.project.minehair.domain.menu.domain.Menu
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import java.time.LocalDateTime

data class MenuContents(

    val menuId: Long,
    val contentsType: ContentsType,
    val contentsUrl: String,
    val orderNo: Int,

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

    fun updateOrderNo(newOrderNo: Int): MenuContents {
        return copy(orderNo = newOrderNo, updatedId = 1L, updatedAt = LocalDateTime.now())
    }

    fun updateFrom(updateRequest: UpdateMenuContentsRequest) = copy(
        menuId = updateRequest.menuId,
        contentsType = updateRequest.contentsType,
        contentsUrl = updateRequest.contentsUrl,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )
}