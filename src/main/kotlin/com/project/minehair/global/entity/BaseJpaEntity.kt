package com.project.minehair.global.entity

import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open val status: Status,

    @Column(name = "created_id", nullable = false)
    open val createdId: Long,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    open val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_id", nullable = false)
    open val updatedId: Long,

    @LastModifiedDate
    @Column(name = "updated_at")
    open val updatedAt: LocalDateTime? = null
) {
    fun isActive(): Boolean = status == Status.ACTIVE
    fun isDeleted(): Boolean = status == Status.DELETED
    fun isInactive(): Boolean = status == Status.INACTIVE
}