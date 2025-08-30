package com.project.minehair.domain.role.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class UpdateRoleMenuRequest(

    @Schema(name = "parentId", description = "부모 메뉴 ID")
    val parentId: Long? = null,
    @Schema(name = "menuName", description = "메뉴명")
    val menuName: String,
    @Schema(name = "menuPath", description = "경로")
    val menuPath: String,
    @Schema(name = "imageUrl", description = "이미지 URL")
    val imageUrl: String? = null,
    @Schema(name = "isVisible", description = "메뉴 노출 여부")
    val isVisible: Boolean,
    @Schema(name = "menuType", description = "메뉴 타입(대(MAJOR), 중(MINOR), 소(SUB))")
    val menuType: String,
    @Schema(name = "orderNo", description = "메뉴 순서")
    val orderNo: Int,
    @Schema(name = "roles", description = "역할 ID 목록")
    val roles: List<Long>,
    @Schema(name = "isContents", description = "컨텐츠 메뉴 여부")
    val isContents: Boolean = false

)