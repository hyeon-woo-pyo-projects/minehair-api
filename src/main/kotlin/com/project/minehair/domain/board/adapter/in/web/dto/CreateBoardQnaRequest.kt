package com.project.minehair.domain.board.adapter.`in`.web.dto

data class CreateBoardQnaRequest(
    val title: String,
    val content: String,
    val author: String?,
) {
    fun withAuthor(author: String): CreateBoardQnaRequest {
        return this.copy(author = author)
    }
}
