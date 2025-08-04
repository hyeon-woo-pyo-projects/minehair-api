package com.project.minehair.domain.auth.adapter.`in`.web.dto

data class LoginResponse(
    val accessToken: String,
    val refreshToken: String,
    val roleCode: String
)