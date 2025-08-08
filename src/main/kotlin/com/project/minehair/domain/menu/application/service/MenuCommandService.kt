package com.project.minehair.domain.menu.application.service

import com.project.minehair.domain.menu.application.port.`in`.MenuCommandUseCase
import com.project.minehair.domain.menu.application.port.out.MenuPersistencePort
import com.project.minehair.domain.menu.domain.Menu
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class MenuCommandService(
    private val menuPersistencePort: MenuPersistencePort
): MenuCommandUseCase {

    override fun createMenu(menu: Menu): Menu {
        return menuPersistencePort.save(menu)
    }
}