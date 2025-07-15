package com.project.minehair.domain.menu.adapter.out.persistence

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

// MenuJpaEntity.kt - JPA 엔티티
@Entity
@Table(name = "menu")
data class MenuJpaEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "parent_id")
    val parentId: Long? = null,

    @Column(name = "name", nullable = false, length = 100)
    val name: String,

    @Column(name = "path", nullable = false, length = 255)
    val path: String,

    @Column(name = "order_no", nullable = false)
    val orderNo: Int = 0,

    @Column(name = "visible", nullable = false)
    val visible: Boolean,

    @Column(name = "status", nullable = false, length = 20)
    val status: String = "active",

    @Column(name = "created_id", nullable = false)
    val createdId: Long,

    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Column(name = "updated_id", nullable = false)
    val updatedId: Long,

    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
)
