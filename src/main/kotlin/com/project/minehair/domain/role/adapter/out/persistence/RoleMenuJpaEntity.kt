package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "role_menu")
data class RoleMenuJpaEntity(

    @Column(name = "role_id", nullable = false)
    val roleId: Long,

    @Column(name = "menu_id", nullable = false)
    val menuId: Long,

    // BaseJpaEntity 필드들 override
    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt)