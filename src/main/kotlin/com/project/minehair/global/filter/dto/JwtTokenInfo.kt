package com.project.minehair.global.filter.dto

data class JwtTokenInfo(
    val id: Long,
    val token: String,
    val userId: String,
    val authorities: List<String>,
    val issuedAt: Long,
    val expiresAt: Long
)