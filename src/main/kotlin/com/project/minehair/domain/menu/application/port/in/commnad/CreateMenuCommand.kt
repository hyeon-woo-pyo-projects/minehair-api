package com.project.minehair.domain.menu.application.port.`in`.commnad

data class CreateMenuCommand(

    val parentId: Long?,
    val name: String,
    val path: String,
    val imageUrl: String?,
    val isVisible: Boolean,
    val menuType: String,
    val orderNo: Int,
    val isManage: Boolean

)
