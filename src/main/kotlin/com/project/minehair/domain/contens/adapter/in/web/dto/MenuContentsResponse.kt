package com.project.minehair.domain.contens.adapter.`in`.web.dto

import com.project.minehair.domain.contens.domain.ContentsType

data class MenuContentsResponse(
    val id: Long,
    val menuId: Long,
    val contentsType: ContentsType,
    val contentsUrl: String,
    val orderNo: Int,
)