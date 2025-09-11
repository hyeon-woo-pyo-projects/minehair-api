package com.project.minehair.domain.board.domain

import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewCategoryRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class BoardReviewCategory(

    val name: String,
    val orderNo: Int,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    fun updateOrderNo(newOrderNo: Int): BoardReviewCategory {
        return copy(orderNo = newOrderNo, updatedId = 1L, updatedAt = LocalDateTime.now())
    }

    fun update(
        request: UpdateBoardReviewCategoryRequest
    ): BoardReviewCategory {
        return this.copy(
            name = request.name,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }

    fun delete(): BoardReviewCategory {
        return this.copy(
            status = Status.deleted,
            updatedAt = LocalDateTime.now()
        )
    }
}
