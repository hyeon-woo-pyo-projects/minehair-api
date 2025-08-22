package com.project.minehair.domain.role.application.port.`in`

import com.project.minehair.domain.role.adapter.`in`.web.dto.CreateRoleMenuRequest
import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleMenuResponse
import com.project.minehair.domain.role.adapter.`in`.web.dto.UpdateRoleMenuRequest

interface RoleMenuUseCase {
    /**
     * 특정 역할에 할당된 메뉴 목록 조회
     */
    fun getMenusByRole(): List<RoleMenuResponse>

    /**
     * 관리자 메뉴에서 메뉴 목록 조회
     */
    fun getMenusByRoleForAdmin(): List<RoleMenuResponse>

    /**
     * 메뉴 생성 및 역할 세팅
     */
    fun createMenuAndAssignRoles(createRoleMenuRequest: CreateRoleMenuRequest)

    /**
     * 메뉴, 역할 세팅 수정
     */
    fun updateMenuRole(menuId: Long, updateRoleMenuRequest: UpdateRoleMenuRequest)

    /**
     * 메뉴 삭제
     */
    fun deleteMenuRole(menuId: Long): List<RoleMenuResponse>

}