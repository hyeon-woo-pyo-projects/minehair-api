package com.project.minehair.domain.menu.application.port.`in`

import com.project.minehair.domain.menu.adapter.`in`.web.dto.ChangeMenuOrderNoRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse

// MenuUseCase.kt - 입력 포트 (CRUD 통합)
interface MenuUseCase {

    /**
     * 메뉴 순서 변경
     */
    fun changeMenuOrderNo(request: List<ChangeMenuOrderNoRequest>): List<MenuResponse>

    /**
     * 컨텐츠용 메뉴 조회
     */
    fun getContentsMenuList(): List<MenuResponse>

}