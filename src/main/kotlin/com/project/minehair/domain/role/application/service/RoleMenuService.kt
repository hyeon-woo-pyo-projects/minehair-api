package com.project.minehair.domain.role.application.service

import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleMenuResponse
import com.project.minehair.domain.role.application.port.`in`.RoleMenuUseCase
import com.project.minehair.domain.role.application.port.out.domain.MenuDomainPort
import com.project.minehair.domain.role.application.port.out.persistence.RoleMenuPersistencePort
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.filter.context.JwtTokenContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RoleMenuService(
    private val roleMenuPersistencePort: RoleMenuPersistencePort,
    private val menuDomainPort: MenuDomainPort
) : RoleMenuUseCase {

    override fun getMenusByRole(): List<RoleMenuResponse> {

        val role = menuDomainPort.getRoleByCode(JwtTokenContext.getAuthorities().first())
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "role");

        // 1. 토큰을 조회하여, roleId를 가져온다
        val roleId = role.id;

        // 1. role_menu 매핑 조회
        val roleMenus = roleMenuPersistencePort.findByRoleId(roleId)

        // 2. 메뉴 ID 목록 추출
        val menuIds = roleMenus.map { it.menuId }

        // 3. 메뉴 정보 조회 (다른 도메인 호출)
        val menus = menuDomainPort.getMenusByIds(menuIds)

        // 4. 매핑하여 응답 생성
        return roleMenus.mapNotNull { roleMenu ->
            val menu = menus.find { it.id == roleMenu.menuId }
            menu?.let {
                RoleMenuResponse(
                    menuId = roleMenu.menuId,
                    parentId = it.parentId,
                    menuName = it.name,
                    menuPath = it.path,
                    menuOrderNo = it.orderNo,
                    menuVisible = it.visible,
                    status = roleMenu.status
                )
            }
        }
    }

    override fun getRoleMenuDetail(roleId: Long, menuId: Long): RoleMenuResponse {
        // 1. role_menu 조회
        val roleMenu = roleMenuPersistencePort.findByRoleIdAndMenuId(roleId, menuId)
            ?: throw IllegalArgumentException("Role-Menu mapping not found")

        // 2. 메뉴 정보 조회
        val menu = menuDomainPort.getMenuById(menuId)
            ?: throw IllegalArgumentException("Menu not found")

        return RoleMenuResponse(
            menuId = roleMenu.menuId,
            parentId = menu.parentId,
            menuName = menu.name,
            menuPath = menu.path,
            menuOrderNo = menu.orderNo,
            menuVisible = menu.visible,
            status = roleMenu.status
        )
    }

    @Transactional
    override fun assignMenusToRole(roleId: Long, menuIds: List<Long>) {
        // 중복 제거 및 기존에 없는 메뉴만 추가
        val existingMenuIds = roleMenuPersistencePort.findByRoleId(roleId)
            .map { it.menuId }
            .toSet()

        val newMenuIds = menuIds.filterNot { existingMenuIds.contains(it) }

        if (newMenuIds.isNotEmpty()) {
            roleMenuPersistencePort.saveRoleMenus(roleId, newMenuIds)
        }
    }

    @Transactional
    override fun replaceRoleMenus(roleId: Long, menuIds: List<Long>) {
        // 1. 기존 매핑 모두 삭제
        roleMenuPersistencePort.deleteByRoleId(roleId)

        // 2. 새로운 매핑 생성
        if (menuIds.isNotEmpty()) {
            roleMenuPersistencePort.saveRoleMenus(roleId, menuIds)
        }
    }

    @Transactional
    override fun removeMenuFromRole(roleId: Long, menuId: Long) {
        roleMenuPersistencePort.deleteByRoleIdAndMenuId(roleId, menuId)
    }
}