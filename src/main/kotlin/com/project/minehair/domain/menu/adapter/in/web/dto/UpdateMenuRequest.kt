package com.project.minehair.domain.menu.adapter.`in`.web.dto

import com.project.minehair.global.enums.Status

// UpdateMenuRequest.kt - 수정 요청 DTO
data class UpdateMenuRequest(
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: Status,
    val updatedId: Long
)
