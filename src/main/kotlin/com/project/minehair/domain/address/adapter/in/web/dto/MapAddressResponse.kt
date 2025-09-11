package com.project.minehair.domain.address.adapter.`in`.web.dto

data class MapAddressResponse(
    val id: Long,
    val lat: Double,
    val lng: Double,
    val address: String,
    val detailAddress: String?
)
