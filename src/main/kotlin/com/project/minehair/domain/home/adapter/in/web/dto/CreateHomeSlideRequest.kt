package com.project.minehair.domain.home.adapter.`in`.web.dto

import com.fasterxml.jackson.annotation.JsonIgnore
import io.swagger.v3.oas.annotations.media.Schema

data class CreateHomeSlideRequest(

    @Schema(description = "이미지 URL", example = "https://example.com/image.jpg")
    val imageUrl: String,

    @Schema(description = "링크 URL", example = "https://example.com")
    val link: String,

    @JsonIgnore
    val orderNo: Int

) {
    fun setOrderNo(orderNo: Int): CreateHomeSlideRequest {
        return this.copy(orderNo = orderNo)
    }
}
