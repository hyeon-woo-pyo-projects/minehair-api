package com.project.minehair.domain.menu.adapter.`in`.web.dto

// CreateMenuRequest.kt - 생성 요청 DTO
data class CreateMenuRequest(
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val createdId: Long
)