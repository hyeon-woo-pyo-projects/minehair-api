package com.project.minehair.global.domain

import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

abstract class BaseDomain(
    open val id: Long? = null,
    open val status: Status,
    open val createdId: Long,
    open val createdAt: LocalDateTime = LocalDateTime.now(),
    open val updatedId: Long,
    open val updatedAt: LocalDateTime? = null
) {
    fun isActive(): Boolean = status == Status.ACTIVE
    fun isDeleted(): Boolean = status == Status.DELETED
    fun isInactive(): Boolean = status == Status.INACTIVE
}