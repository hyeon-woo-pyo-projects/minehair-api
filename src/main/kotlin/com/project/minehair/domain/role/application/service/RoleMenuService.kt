package com.project.minehair.domain.role.application.service

import com.project.minehair.domain.role.adapter.`in`.web.dto.CreateRoleMenuRequest
import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleMenuResponse
import com.project.minehair.domain.role.adapter.`in`.web.dto.UpdateRoleMenuRequest
import com.project.minehair.domain.role.application.port.`in`.RoleMenuUseCase
import com.project.minehair.domain.role.application.port.out.domain.RoleMenuDomainPort
import com.project.minehair.domain.role.application.port.out.persistence.RoleMenuPersistencePort
import com.project.minehair.domain.role.domain.RoleMenu
import com.project.minehair.global.domain.inter.InterDomainMenuInfo
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.enums.Status
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.filter.context.JwtTokenContext
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class RoleMenuService(
    private val roleMenuPersistencePort: RoleMenuPersistencePort,
    private val menuDomainPort: RoleMenuDomainPort
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
                roleMenu.id?.let { it1 ->
                    RoleMenuResponse(
                        id = it1,
                        menuId = roleMenu.menuId,
                        parentId = it.parentId,
                        menuName = it.name,
                        menuPath = it.path,
                        imageUrl = it.imageUrl,
                        menuVisible = it.isVisible,
                        menuType = it.menuType,
                        menuOrderNo = it.orderNo,
                        isContents = it.isContents
                    )
                }
            }
        }.sortedBy { it.menuOrderNo }
    }

    override fun getMenusByRoleForAdmin(): List<RoleMenuResponse> {
        // 1. role_menu 매핑 조회
        val roleMenus = roleMenuPersistencePort.findAll()

        // 2. 메뉴 ID 목록 추출
        val menuIds = roleMenus.map { it.menuId }

        // 3. 메뉴 정보 조회 (다른 도메인 호출)
        val menus = menuDomainPort.getMenusByIds(menuIds)
            .filter { it.isManage }

        // 4. menus에 role매핑하여 응답 생성
        return menus.mapNotNull { menu ->
            roleMenus.find { it.menuId == menu.id }?.let { roleMenu ->
                RoleMenuResponse(
                    id = null,
                    menuId = roleMenu.menuId,
                    parentId = menu.parentId,
                    menuName = menu.name,
                    menuPath = menu.path,
                    imageUrl = menu.imageUrl,
                    menuVisible = menu.isVisible,
                    menuType = menu.menuType,
                    menuOrderNo = menu.orderNo,
                    // 역할 ID 목록 추가(roleMenus를 그룹핑하여 리스트 생성)
                    roleIdList = roleMenus.filter { rm -> rm.menuId == roleMenu.menuId }
                        .map { it.roleId },
                    isContents = menu.isContents
                )
            }
        }.sortedBy { it.menuOrderNo }
    }

    @Transactional
    override fun createMenuAndAssignRoles(createRoleMenuRequest: CreateRoleMenuRequest) {
        // 1. 메뉴 생성
        val maxOrderNo = menuDomainPort.getMaxOrderNo()
        val interDomainMenuInfo = InterDomainMenuInfo(
            id = null,
            parentId = createRoleMenuRequest.parentId,
            name = createRoleMenuRequest.menuName,
            path = createRoleMenuRequest.menuPath,
            imageUrl = null,
            isVisible = createRoleMenuRequest.isVisible,
            menuType = createRoleMenuRequest.menuType,
            orderNo = maxOrderNo.plus(1),
            isManage = true,
            isContents = createRoleMenuRequest.isContents
        )
        val menu = menuDomainPort.createMenu(interDomainMenuInfo)

        // 2. 역할 이 있는지 체크 (TODO)

        // 3. role_menu 매핑 저장
        createRoleMenuRequest.roles.forEach { roleId ->
            val roleMenu = RoleMenu(
                id = null,
                roleId = roleId,
                menuId = menu.id ?: throw BusinessException(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    "Menu ID is null after creation"
                ),
                status = Status.active, // 기본 상태를 active로 설정
                createdId = 1L,
                createdAt = LocalDateTime.now(),
                updatedId = 0L,
                updatedAt = null,
            )
            roleMenuPersistencePort.save(roleMenu)
        }
    }

    /**
     * 메뉴, 역할 세팅 수정
     */
    @Transactional
    override fun updateMenuRole(menuId: Long, updateRoleMenuRequest: UpdateRoleMenuRequest) {

        // 1. 메뉴 정보 업데이트
        val interDomainMenuInfo = InterDomainMenuInfo(
            id = menuId,
            parentId = updateRoleMenuRequest.parentId,
            name = updateRoleMenuRequest.menuName,
            path = updateRoleMenuRequest.menuPath,
            imageUrl = updateRoleMenuRequest.imageUrl,
            isVisible = updateRoleMenuRequest.isVisible,
            menuType = updateRoleMenuRequest.menuType,
            orderNo = updateRoleMenuRequest.orderNo,
            isManage = true,
            isContents = updateRoleMenuRequest.isContents
        )
        val updatedMenu = menuDomainPort.updateMenu(interDomainMenuInfo)
        val updatedMenuId = updatedMenu.id ?: throw BusinessException(
            ErrorCode.INTERNAL_SERVER_ERROR,
            "Menu ID is null after update"
        )

        // 2. 기존 매핑들을 soft delete 처리
        roleMenuPersistencePort.findByMenuId(updatedMenuId)
            .forEach { existingRoleMenu ->
                val deletedRoleMenu = existingRoleMenu.delete()
                roleMenuPersistencePort.save(deletedRoleMenu)
            }

        // 3. 새로운 매핑들 생성
        updateRoleMenuRequest.roles.forEach { roleId ->
            val roleMenu = RoleMenu(
                id = null,
                roleId = roleId,
                menuId = updatedMenuId,
                status = Status.active,
                createdId = 1L,
                createdAt = LocalDateTime.now(),
                updatedId = 0L,
                updatedAt = null
            )
            roleMenuPersistencePort.save(roleMenu)
        }
    }

    @Transactional
    override fun deleteMenuRole(menuId: Long): List<RoleMenuResponse>{
        // 1. 메뉴 정보 조회
        val menu = menuDomainPort.getMenuById(menuId)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "Menu not found with ID: $menuId")

        // 2. 역할 매핑 조회
        val roleMenus = roleMenuPersistencePort.findByMenuId(menuId)


        // 3. 역할 매핑 삭제
        val deletedList = roleMenuPersistencePort.delete(roleMenus)

        // 4. 응답 생성
        return deletedList.map { roleMenu ->
            RoleMenuResponse(
                id = roleMenu.id,
                menuId = roleMenu.menuId,
                parentId = menu.parentId,
                menuName = menu.name,
                menuPath = menu.path,
                imageUrl = menu.imageUrl,
                menuVisible = menu.isVisible,
                menuType = menu.menuType,
                menuOrderNo = menu.orderNo,
                isContents = menu.isContents
            )
        }


    }

}