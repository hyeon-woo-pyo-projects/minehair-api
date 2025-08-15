package com.project.minehair.domain.menu.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

data class ChangeMenuOrderNoRequest(

    @Schema(name = "menuId", description = "메뉴 ID")
    @field:NotNull(message = "메뉴 ID는 필수입니다.")
    val menuId: Long,

    @Schema(name = "orderNo", description = "메뉴 순서 번호")
    @field:NotNull(message = "메뉴 순서 번호는 필수입니다.")
    val orderNo: Int

)
