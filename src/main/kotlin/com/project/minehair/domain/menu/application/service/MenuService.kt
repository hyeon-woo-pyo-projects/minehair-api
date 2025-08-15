package com.project.minehair.domain.menu.application.service

import com.project.minehair.domain.menu.adapter.`in`.web.dto.ChangeMenuOrderNoRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.adapter.out.persistence.MenuMapper
import com.project.minehair.domain.menu.application.port.`in`.MenuUseCase
import com.project.minehair.domain.menu.application.port.out.MenuPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

// MenuService.kt - 애플리케이션 서비스
@Service
@Transactional
class MenuService(
    private val menuPersistencePort: MenuPersistencePort,
    private val menuMapper: MenuMapper
) : MenuUseCase {

    /**
     * 메뉴 순서 변경
     */
    override fun changeMenuOrderNo(request: List<ChangeMenuOrderNoRequest>): List<MenuResponse> {
        // id set 세팅
        val menuIds = request.map { it.menuId }.toList()

        // find menus by ids
        val menus = menuPersistencePort.findByIds(menuIds)

        // request 를 id(key), orderNo(value) map 형태로 변환
        val requestMap = request.associate { it.menuId to it.orderNo }

        // 메뉴 순서 변경
        val menusForUpdate = menus.map { menu ->
            // requestMap 에서 해당 메뉴의 orderNo 가져오기, 없으면 건너뛰기
            val newOrderNo = requestMap[menu.id] ?: return@map menu

            // 해당 메뉴의 orderNo가 이미 같은 경우는 건너뛰기
            if (menu.orderNo == newOrderNo) {
                return@map menu
            }
            // orderNo가 변경된 경우, 기존 메뉴의 orderNo를 업데이트
            menu.updateOrderNo(newOrderNo)
        }.toList()

        // 변경된 메뉴들을 저장
        val updatedMenus = menuPersistencePort.updateAll(menusForUpdate)
        return menuMapper.toResponseList(updatedMenus)
    }


//    override fun createMenu(request: CreateMenuRequest): MenuResponse {
//        val menu = Menu(
//            id = null,
//            parentId = request.parentId,
//            name = request.name,
//            path = request.path,
//            imageUrl = request.imageUrl,
//            isVisible = request.isVisible,
//            menuType = request.menuType,
//            orderNo = request.orderNo,
//            status = Status.active,
//            createdId = request.createdId,
//            createdAt = LocalDateTime.now(),
//            updatedId = request.createdId,
//            updatedAt = null
//        )
//
//        val savedMenu = menuPersistencePort.save(menu)
//        return toResponse(savedMenu)
//    }
//
//    override fun getMenuById(id: Long): MenuResponse {
//        val menu = menuPersistencePort.findById(id)
//            ?: throw IllegalArgumentException("Menu not found: ${id}")
//        return toResponse(menu)
//    }
//
//    override fun getAllMenus(): List<MenuResponse> {
//        return menuPersistencePort.findAll().map { toResponse(it) }
//    }
//
//    override fun updateMenu(id: Long, request: UpdateMenuRequest): MenuResponse {
//        val existingMenu = menuPersistencePort.findById(id)
//            ?: throw IllegalArgumentException("Menu not found: ${id}")
//
//        val updatedMenu = existingMenu.copy(
//            parentId = request.parentId,
//            name = request.name,
//            path = request.path,
//            imageUrl = request.imageUrl,
//            isVisible = request.isVisible,
//            menuType = request.menuType,
//            orderNo = request.orderNo,
//            status = request.status,
//            updatedId = request.updatedId,
//            updatedAt = LocalDateTime.now()
//        )
//
//        val savedMenu = menuPersistencePort.save(updatedMenu)
//        return toResponse(savedMenu)
//    }
//
//    override fun deleteMenu(id: Long) {
//        menuPersistencePort.deleteById(id)
//    }

//    private fun toResponse(menu: Menu): MenuResponse {
//        return MenuResponse(
//            id = menu.id,
//            parentId = menu.parentId,
//            name = menu.name,
//            path = menu.path,
//            imageUrl = menu.imageUrl,
//            isVisible = menu.isVisible,
//            menuType = menu.menuType,
//            orderNo = menu.orderNo,
//            status = menu.status.name,
//            createdId = menu.createdId,
//            createdAt = menu.createdAt,
//            updatedId = menu.updatedId,
//            updatedAt = menu.updatedAt
//        )
//    }
}