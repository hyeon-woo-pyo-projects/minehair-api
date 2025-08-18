package com.project.minehair.global.domain

import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

abstract class BaseDomain(
    open val id: Long?,
    open val status: Status,
    open val createdId: Long,
    open val createdAt: LocalDateTime,
    open val updatedId: Long?,
    open val updatedAt: LocalDateTime?
) {
    fun isActive(): Boolean = status == Status.active
    fun isInactive(): Boolean = status == Status.inactive
    fun isDeleted(): Boolean = status == Status.deleted
}