package com.project.minehair.domain.board.adapter.`in`.web.dto

data class BoardQnaResponse(
    val id: Long,
    val title: String,
    val content: String,
    val author: String,
    val viewCount: Int,
)
