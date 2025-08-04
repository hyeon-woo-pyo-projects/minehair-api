package com.project.minehair.domain.role.adapter.`in`.web.dto

import com.project.minehair.global.enums.Status

data class RoleMenuResponse(
    val menuId: Long,
    val parentId: Long?,
    val menuName: String,
    val menuPath: String,
    val menuOrderNo: Int,
    val menuVisible: Boolean,
    val status: Status
)