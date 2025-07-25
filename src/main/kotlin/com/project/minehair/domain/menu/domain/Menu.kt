package com.project.minehair.domain.menu.domain

import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class Menu(
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    // Menu 고유 기능
    fun hide() = copy(visible = false)
    fun show() = copy(visible = true)

    // 공통 상태 변경 (불변성 보장)
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

    fun delete(updatedId: Long) = copy(
        status = Status.deleted,
        updatedId = updatedId,
        updatedAt = LocalDateTime.now()
    )
}