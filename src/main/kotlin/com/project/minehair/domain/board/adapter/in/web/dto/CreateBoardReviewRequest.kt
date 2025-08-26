package com.project.minehair.domain.board.adapter.`in`.web.dto

data class CreateBoardReviewRequest(
    val title: String,
    val content: String,
    val author: String?,
) {
    fun withAuthor(author: String): CreateBoardReviewRequest {
        return this.copy(author = author)
    }
}
