package com.project.minehair.domain.role.adapter.`in`.web.dto

data class RoleMenuResponse(
    val id: Long?,
    val menuId: Long,
    val parentId: Long?,
    val menuName: String,
    val menuPath: String,
    val imageUrl: String?,
    val menuVisible: Boolean,
    val menuType: String,
    val menuOrderNo: Int,
    val roleIdList: List<Long>? = null,
)