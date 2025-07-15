package com.project.minehair.domain.menu.application.service

import com.project.minehair.domain.menu.adapter.`in`.web.dto.CreateMenuRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.adapter.`in`.web.dto.UpdateMenuRequest
import com.project.minehair.domain.menu.application.port.`in`.MenuUseCase
import com.project.minehair.domain.menu.application.port.out.MenuPersistencePort
import com.project.minehair.domain.menu.domain.Menu
import com.project.minehair.domain.menu.domain.MenuStatus
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

// MenuService.kt - 애플리케이션 서비스
@Service
@Transactional
class MenuService(
    private val menuPersistencePort: MenuPersistencePort
) : MenuUseCase {

    override fun createMenu(request: CreateMenuRequest): MenuResponse {
        val menu = Menu(
            id = null,
            parentId = request.parentId,
            name = request.name,
            path = request.path,
            orderNo = request.orderNo,
            visible = request.visible,
            status = MenuStatus.ACTIVE,
            createdId = request.createdId,
            createdAt = LocalDateTime.now(),
            updatedId = request.createdId,
            updatedAt = null
        )

        val savedMenu = menuPersistencePort.save(menu)
        return toResponse(savedMenu)
    }

    override fun getMenuById(id: Long): MenuResponse {
        val menu = menuPersistencePort.findById(id)
            ?: throw IllegalArgumentException("Menu not found: ${id}")
        return toResponse(menu)
    }

    override fun getAllMenus(): List<MenuResponse> {
        return menuPersistencePort.findAll().map { toResponse(it) }
    }

    override fun updateMenu(id: Long, request: UpdateMenuRequest): MenuResponse {
        val existingMenu = menuPersistencePort.findById(id)
            ?: throw IllegalArgumentException("Menu not found: ${id}")

        val updatedMenu = existingMenu.copy(
            parentId = request.parentId,
            name = request.name,
            path = request.path,
            orderNo = request.orderNo,
            visible = request.visible,
            status = MenuStatus.valueOf(request.status),
            updatedId = request.updatedId,
            updatedAt = LocalDateTime.now()
        )

        val savedMenu = menuPersistencePort.save(updatedMenu)
        return toResponse(savedMenu)
    }

    override fun deleteMenu(id: Long) {
        menuPersistencePort.deleteById(id)
    }

    private fun toResponse(menu: Menu): MenuResponse {
        return MenuResponse(
            id = menu.id,
            parentId = menu.parentId,
            name = menu.name,
            path = menu.path,
            orderNo = menu.orderNo,
            visible = menu.visible,
            status = menu.status.name,
            createdId = menu.createdId,
            createdAt = menu.createdAt,
            updatedId = menu.updatedId,
            updatedAt = menu.updatedAt
        )
    }
}