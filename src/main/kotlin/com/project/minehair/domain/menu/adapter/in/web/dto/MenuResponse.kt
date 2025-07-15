package com.project.minehair.domain.menu.adapter.`in`.web.dto

import java.time.LocalDateTime

// MenuResponse.kt - 응답 DTO
data class MenuResponse(
    val id: Long?,
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: String,
    val createdId: Long,
    val createdAt: LocalDateTime,
    val updatedId: Long,
    val updatedAt: LocalDateTime?
)