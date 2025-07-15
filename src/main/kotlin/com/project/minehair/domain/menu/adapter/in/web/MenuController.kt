package com.project.minehair.domain.menu.adapter.`in`.web

import com.project.minehair.domain.menu.adapter.`in`.web.dto.CreateMenuRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.adapter.`in`.web.dto.UpdateMenuRequest
import com.project.minehair.domain.menu.application.port.`in`.MenuUseCase
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// MenuController.kt - REST 컨트롤러
@RestController
@RequestMapping("/api/menus")
class MenuController(
    private val menuUseCase: MenuUseCase
) {
    @PostMapping
    fun createMenu(@RequestBody request: CreateMenuRequest): MenuResponse {
        return menuUseCase.createMenu(request)
    }

    @GetMapping("/{id}")
    fun getMenu(@PathVariable id: Long): MenuResponse {
        return menuUseCase.getMenuById(id)
    }

    @GetMapping
    fun getAllMenus(): List<MenuResponse> {
        return menuUseCase.getAllMenus()
    }

    @PutMapping("/{id}")
    fun updateMenu(@PathVariable id: Long, @RequestBody request: UpdateMenuRequest): MenuResponse {
        return menuUseCase.updateMenu(id, request)
    }

    @DeleteMapping("/{id}")
    fun deleteMenu(@PathVariable id: Long) {
        menuUseCase.deleteMenu(id)
    }
}