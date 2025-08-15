package com.project.minehair.domain.menu.application.port.`in`

import com.project.minehair.domain.menu.adapter.`in`.web.dto.ChangeMenuOrderNoRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse

// MenuUseCase.kt - 입력 포트 (CRUD 통합)
interface MenuUseCase {

    /**
     * 메뉴 순서 변경
     */
    fun changeMenuOrderNo(request: List<ChangeMenuOrderNoRequest>): List<MenuResponse>

//    fun createMenu(request: CreateMenuRequest): MenuResponse
//    fun getMenuById(id: Long): MenuResponse
//    fun getAllMenus(): List<MenuResponse>
//    fun updateMenu(id: Long, request: UpdateMenuRequest): MenuResponse
//    fun deleteMenu(id: Long)
}