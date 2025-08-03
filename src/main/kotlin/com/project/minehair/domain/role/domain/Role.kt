package com.project.minehair.domain.role.domain

import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class Role(
    val id: Long? = null,
    val code: String,
    val name: String,
    val description: String? = null,
    val priority: Int = 0,
    val status: Status,
    val createdId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedId: Long,
    val updatedAt: LocalDateTime? = null
)