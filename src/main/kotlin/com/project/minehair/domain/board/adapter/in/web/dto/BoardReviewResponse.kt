package com.project.minehair.domain.board.adapter.`in`.web.dto

data class BoardReviewResponse(

    val id: Long,
    val categoryId: Long,
    val title: String,
    val content: String?,
    val imageUrl: String?,

)
