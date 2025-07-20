package com.project.minehair.domain.menu.application.service

import com.project.minehair.domain.menu.application.port.`in`.MenuQueryUseCase
import com.project.minehair.domain.menu.application.port.out.MenuPersistencePort
import com.project.minehair.domain.menu.domain.Menu
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Menu 도메인 간 통신용 조회 서비스
 * 외부 도메인에서 Menu Entity를 조회할 때 사용
 * MenuService(CRUD용)와 분리하여 관심사 분리
 */
@Service
@Transactional(readOnly = true)
class MenuQueryService(
    private val menuPersistencePort: MenuPersistencePort
) : MenuQueryUseCase {

    /**
     * 메뉴 ID 목록으로 메뉴들을 조회
     */
    override fun findMenusByIds(menuIds: List<Long>): List<Menu> {
        if (menuIds.isEmpty()) {
            return emptyList()
        }
        return menuPersistencePort.findByIds(menuIds)
    }

    /**
     * 단일 메뉴 ID로 메뉴 조회
     */
    override fun findMenuById(menuId: Long): Menu {
        return menuPersistencePort.findById(menuId)
            ?: throw IllegalArgumentException("Menu not found with id: $menuId")
    }

    /**
     * 활성화된 메뉴들만 조회
     */
    override fun findActiveMenusByIds(menuIds: List<Long>): List<Menu> {
        if (menuIds.isEmpty()) {
            return emptyList()
        }
        return menuPersistencePort.findByIds(menuIds)
            .filter { it.visible }
    }

    /**
     * 부모 메뉴 ID로 하위 메뉴들 조회
     */
    override fun findChildMenus(parentMenuId: Long): List<Menu> {
        return menuPersistencePort.findByParentId(parentMenuId)
    }
}