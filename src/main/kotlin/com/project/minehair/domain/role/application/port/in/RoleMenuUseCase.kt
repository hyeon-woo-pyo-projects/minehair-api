package com.project.minehair.domain.role.application.port.`in`

import com.project.minehair.domain.role.adapter.`in`.web.dto.CreateRoleMenuRequest
import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleMenuResponse

interface RoleMenuUseCase {
    /**
     * 특정 역할에 할당된 메뉴 목록 조회
     */
    fun getMenusByRole(): List<RoleMenuResponse>

    /**
     * 메뉴 생성 및 역할 세팅
     */
    fun createMenuAndAssignRoles(createRoleMenuRequest: CreateRoleMenuRequest)

//    /**
//     * 특정 역할의 특정 메뉴 할당 정보 상세 조회
//     */
//    fun getRoleMenuDetail(roleId: Long, menuId: Long): RoleMenuResponse
//
//    /**
//     * 역할에 메뉴들 할당 (추가)
//     */
//    fun assignMenusToRole(roleId: Long, menuIds: List<Long>)
//
//    /**
//     * 역할의 메뉴 전체 교체
//     */
//    fun replaceRoleMenus(roleId: Long, menuIds: List<Long>)
//
//    /**
//     * 역할에서 특정 메뉴 제거
//     */
//    fun removeMenuFromRole(roleId: Long, menuId: Long)
}