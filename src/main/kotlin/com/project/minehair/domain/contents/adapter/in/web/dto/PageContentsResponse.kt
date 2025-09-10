package com.project.minehair.domain.contents.adapter.`in`.web.dto

import com.project.minehair.domain.contents.domain.ContentsType

data class PageContentsResponse(
    val id: Long,
    val menuId: Long,
    val pageUrl: String,
    val contentsType: ContentsType,
    val contentsUrl: String,
    val orderNo: Int,
    val videoBackGroundUrl: String?,
    val consultingBackGroundUrl: String?,
)