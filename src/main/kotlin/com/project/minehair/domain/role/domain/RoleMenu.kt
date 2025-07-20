package com.project.minehair.domain.role.domain

import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class RoleMenu(
    val id: Long? = null,
    val roleId: Long,
    val menuId: Long,
    val status: Status,
    val createdId: Long,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedId: Long,
    val updatedAt: LocalDateTime? = null
) {

}