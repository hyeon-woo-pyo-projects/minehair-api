package com.project.minehair.domain.menu.adapter.`in`.web.dto

// MenuResponse.kt - 응답 DTO
data class MenuResponse(
    val id: Long?,
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val imageUrl: String?,
    val isVisible: Boolean,
    val menuType: String,
)