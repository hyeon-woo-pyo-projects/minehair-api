package com.project.minehair.domain.menu.adapter.`in`.web.dto

import com.project.minehair.domain.role.domain.RoleMenu
import java.time.LocalDateTime

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
    val status: String,
    val createdId: Long,
    val createdAt: LocalDateTime,
    val updatedId: Long,
    val updatedAt: LocalDateTime?
)