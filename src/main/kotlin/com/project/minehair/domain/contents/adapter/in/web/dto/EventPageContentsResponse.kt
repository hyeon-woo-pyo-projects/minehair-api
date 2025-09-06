package com.project.minehair.domain.contents.adapter.`in`.web.dto

import com.project.minehair.domain.contents.domain.EventPageContentsType

data class EventPageContentsResponse(
    val id: Long,
    val contentsType: EventPageContentsType,
    val orderNo: Int,
    val slideOrderNo: Int,
    val imageUrl: String,
    val linkUrl: String,
    val textContent: String?,
    val isAddPost: Boolean
)