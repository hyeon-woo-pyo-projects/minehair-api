package com.project.minehair.domain.board.domain

import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class BoardReview(

    val title: String,
    val content: String,
    val author: String = "",
    val viewCount: Int = 0,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {
    /**
     * 조회수를 1 증가시킨 새로운 객체 반환
     */
    fun incrementViewCount(): BoardReview {
        return this.copy(
            viewCount = this.viewCount + 1,
            updatedAt = LocalDateTime.now()
        )
    }

    /**
     * 게시글 정보를 업데이트한 새로운 객체 반환
     */
    fun update(
        request: UpdateBoardReviewRequest
    ): BoardReview {
        return this.copy(
            title = request.title,
            content = request.content,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }

    fun delete(): BoardReview {
        return this.copy(
            status = Status.deleted,
            updatedAt = LocalDateTime.now()
        )
    }
}
