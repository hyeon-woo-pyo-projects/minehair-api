package com.project.minehair.domain.menu.domain

import java.time.LocalDateTime

// Menu.kt - 도메인 엔티티
data class Menu(
    val id: Long?,
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: MenuStatus,
    val createdId: Long,
    val createdAt: LocalDateTime,
    val updatedId: Long,
    val updatedAt: LocalDateTime?
) {
    fun activate() = copy(status = MenuStatus.ACTIVE)
    fun deactivate() = copy(status = MenuStatus.INACTIVE)
    fun delete() = copy(status = MenuStatus.DELETED)
    fun hide() = copy(visible = false)
    fun show() = copy(visible = true)
}