package com.project.minehair.domain.address.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class CreateMapAddressRequest(
    @Schema(description = "위도", example = "37.123456")
    @field:NotNull(message = "위도를 입력해주세요.")
    val lat: Double,

    @Schema(description = "경도", example = "127.123456")
    @field:NotNull(message = "경도를 입력해주세요.")
    val lng: Double,

    @Schema(description = "주소", example = "서울특별시 강남구 테헤란로 123")
    @field:NotNull(message = "주소를 입력해주세요.")
    val address: String,

    @Schema(description = "상세주소", example = "101동 202호", nullable = true)
    val detailAddress: String?
)
