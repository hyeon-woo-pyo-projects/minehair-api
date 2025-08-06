package com.project.minehair.domain.banner.adapter.`in`.web.dto

data class BannerUpdateRequest(
    val content: String,
    val color: String,
    val textColor: String,
    val link: String,
    val imageUrl: String?,
    val isPost: Boolean
)
