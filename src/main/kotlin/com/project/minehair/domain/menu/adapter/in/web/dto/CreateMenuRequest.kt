package com.project.minehair.domain.menu.adapter.`in`.web.dto

// CreateMenuRequest.kt - 생성 요청 DTO
data class CreateMenuRequest(
    val parentId: Long?,
    val name: String,
    val path: String,
    val imageUrl: String?,
    val isVisible: Boolean,
    val menuType: String,
    val orderNo: Int,
    val createdId: Long
)