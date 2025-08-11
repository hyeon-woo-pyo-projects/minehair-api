package com.project.minehair.domain.consultation.adapter.`in`.web.dto

data class ConsultationCategoryResponse(
    val id: Long,
    val code: String,
    val name: String,
    val description: String? = null
)
