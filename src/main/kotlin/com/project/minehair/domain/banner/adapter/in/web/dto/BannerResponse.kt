package com.project.minehair.domain.banner.adapter.`in`.web.dto

data class BannerResponse(
    val content: String,
    val color: String,
    val link: String,
    val imageUrl: String?,
    val isPost: Boolean,
)
