package com.project.minehair.domain.user.adapter.`in`.web.dto

import com.project.minehair.domain.user.domain.User
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

// 사용자 응답 DTO
data class UserResponse(
    val id: Long,
    val userId: String,
    val email: String?,
    val name: String,
    val phoneNumber: String?,
    val status: Status,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
) {
    companion object {
        fun from(user: User): UserResponse {
            return UserResponse(
                id = user.id!!,
                userId = user.userId,
                email = user.email,
                name = user.name,
                phoneNumber = user.phone,
                status = user.status,
                createdAt = user.createdAt,
                updatedAt = user.updatedAt
            )
        }
    }
}