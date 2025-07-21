package com.project.minehair.domain.user.adapter.`in`.web.dto

data class UserCreateRequest(
    val userId: String,
    val password: String,
    val name: String,
    val userType: String
)