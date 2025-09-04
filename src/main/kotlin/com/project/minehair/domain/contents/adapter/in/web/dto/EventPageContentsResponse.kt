package com.project.minehair.domain.contents.adapter.`in`.web.dto

data class EventPageContentsResponse(
    val id: Long,
    val orderNo: Int,
    val slideOrderNo: Int,
    val imageUrl: String,
    val linkUrl: String,
    val textContent: String?,
    val isAddPost: Boolean
)