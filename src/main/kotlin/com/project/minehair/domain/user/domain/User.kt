package com.project.minehair.domain.user.domain

import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class User(
    val id: Long? = null,
    val userId: String,
    val email: String,
    val password: String,
    val name: String,
    val phoneNumber: String?,
    val userType: UserType,
    val status: Status,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val updatedAt: LocalDateTime = LocalDateTime.now()
) {
    fun update(
        email: String,
        name: String,
        phoneNumber: String?,
        userType: UserType
    ): User {
        return copy(
            email = email,
            name = name,
            phoneNumber = phoneNumber,
            userType = userType,
            updatedAt = LocalDateTime.now()
        )
    }

    fun changeStatus(status: Status): User {
        return copy(
            status = status,
            updatedAt = LocalDateTime.now()
        )
    }

    fun changePassword(newPassword: String): User {
        return copy(
            password = newPassword,
            updatedAt = LocalDateTime.now()
        )
    }

    fun delete(): User {
        return copy(
            status = Status.deleted,
            updatedAt = LocalDateTime.now()
        )
    }
}