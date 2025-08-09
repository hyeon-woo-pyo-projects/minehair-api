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
    fun delete() = copy(
        status = Status.deleted,
        updatedId = 1L,
        updatedAt = LocalDateTime.now()
    )

    // 추가로 유용한 메서드들
    fun activate(updatedId: Long) = copy(
        status = Status.active,
        updatedId = updatedId,
        updatedAt = LocalDateTime.now()
    )

    fun inactivate(updatedId: Long) = copy(
        status = Status.inactive,
        updatedId = updatedId,
        updatedAt = LocalDateTime.now()
    )
}