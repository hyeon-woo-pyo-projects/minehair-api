package com.project.minehair.domain.user.adapter.`in`.web.dto

import com.project.minehair.domain.user.domain.UserType

// 회원가입 요청 DTO
data class UserCreateRequest(

    val userId: String,
    val email: String,
    val password: String,
    val name: String,
    val phone: String,
    val userType: UserType = UserType.member

)