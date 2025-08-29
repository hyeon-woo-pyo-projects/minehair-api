package com.project.minehair.domain.menu.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

// MenuJpaEntity.kt - JPA 엔티티
@Entity
@Table(name = "menu")
data class MenuJpaEntity(
    @Column(name = "parent_id")
    val parentId: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "path", nullable = false)
    val path: String,

    @Column(name = "image_url")
    val imageUrl: String?,

    @Column(name = "is_visible", nullable = false)
    val isVisible: Boolean,

    @Column(name = "menu_type", nullable = false)
    val menuType: String,

    @Column(name = "order_no", nullable = false)
    val orderNo: Int = 0,

    @Column(name = "is_manage", nullable = false)
    val isManage: Boolean = true,

    // BaseJpaEntity 필드들 override
    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt)
