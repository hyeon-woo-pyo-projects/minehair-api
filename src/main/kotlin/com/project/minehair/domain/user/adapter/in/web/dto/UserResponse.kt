package com.project.minehair.domain.user.adapter.`in`.web.dto

import java.time.LocalDate

// 사용자 응답 DTO
data class UserResponse(
    val id: Long,
    val userId: String,
    val email: String?,
    val name: String,
    val phoneNumber: String?,
    val birthDate: LocalDate
)