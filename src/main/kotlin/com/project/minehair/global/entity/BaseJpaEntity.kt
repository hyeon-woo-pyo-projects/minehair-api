package com.project.minehair.global.entity

import com.project.minehair.global.enums.Status
import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    open val id: Long?,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    open val status: Status,

    @Column(name = "created_id", nullable = false)
    open val createdId: Long,

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    open val createdAt: LocalDateTime,

    @Column(name = "updated_id")
    open val updatedId: Long?,

    @LastModifiedDate
    @Column(name = "updated_at")
    open val updatedAt: LocalDateTime?
) {
    fun isActive(): Boolean = status == Status.active
    fun isInactive(): Boolean = status == Status.inactive
    fun isDeleted(): Boolean = status == Status.deleted
}