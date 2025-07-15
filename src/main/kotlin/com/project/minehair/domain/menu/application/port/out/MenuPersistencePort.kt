package com.project.minehair.domain.menu.application.port.out

import com.project.minehair.domain.menu.domain.Menu

// MenuPersistencePort.kt - 출력 포트
interface MenuPersistencePort {
    fun save(menu: Menu): Menu
    fun findById(id: Long): Menu?
    fun findAll(): List<Menu>
    fun deleteById(id: Long)
}