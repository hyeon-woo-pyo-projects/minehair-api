package com.project.minehair.domain.menu.adapter.`in`.domain

import com.project.minehair.domain.menu.adapter.out.persistence.MenuMapper
import com.project.minehair.domain.menu.application.port.`in`.MenuCommandUseCase
import com.project.minehair.domain.menu.application.port.`in`.commnad.CreateMenuCommand
import com.project.minehair.domain.menu.domain.Menu
import org.springframework.stereotype.Component

@Component
class MenuCommandAdapter(
    private val menuCommandUseCase: MenuCommandUseCase,
    private val menuMapper: MenuMapper
) {

    fun createMenu(command: CreateMenuCommand): Menu {
        val menu = menuMapper.toDomain(command)
        return menuCommandUseCase.createMenu(menu)
    }

}