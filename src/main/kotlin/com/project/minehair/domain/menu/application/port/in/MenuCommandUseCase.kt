package com.project.minehair.domain.menu.application.port.`in`

import com.project.minehair.domain.menu.domain.Menu

interface MenuCommandUseCase {
    /**
     * 메뉴 생성
     */
    fun createMenu(menu: Menu) : Menu

    /**
     * 메뉴 수정
     */
    fun updateMenu(menu: Menu) : Menu
}