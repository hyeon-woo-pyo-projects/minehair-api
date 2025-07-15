package com.project.minehair.domain.menu.adapter.`in`.web.dto

// UpdateMenuRequest.kt - 수정 요청 DTO
data class UpdateMenuRequest(
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: String,
    val updatedId: Long
)
