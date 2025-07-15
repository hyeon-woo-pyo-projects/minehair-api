package com.project.minehair.domain.menu.application.port.`in`

import com.project.minehair.domain.menu.adapter.`in`.web.dto.CreateMenuRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.adapter.`in`.web.dto.UpdateMenuRequest

// MenuUseCase.kt - 입력 포트 (CRUD 통합)
interface MenuUseCase {
    fun createMenu(request: CreateMenuRequest): MenuResponse
    fun getMenuById(id: Long): MenuResponse
    fun getAllMenus(): List<MenuResponse>
    fun updateMenu(id: Long, request: UpdateMenuRequest): MenuResponse
    fun deleteMenu(id: Long)
}