package com.project.minehair.domain.role.application.port.out.persistence

import com.project.minehair.domain.role.domain.RoleMenu

interface RoleMenuPersistencePort {

    /**
     * 역할 ID로 역할-메뉴 매핑 목록 조회
     */
    fun findByRoleId(roleId: Long): List<RoleMenu>

    /**
     * 역할 ID와 메뉴 ID로 특정 매핑 조회
     */
    fun findByRoleIdAndMenuId(roleId: Long, menuId: Long): RoleMenu?

    /**
     * 역할에 메뉴들 할당 (다중 저장)
     */
    fun saveRoleMenus(roleId: Long, menuIds: List<Long>)

    /**
     * 역할의 모든 메뉴 매핑 삭제
     */
    fun deleteByRoleId(roleId: Long)

    /**
     * 특정 역할-메뉴 매핑 삭제
     */
    fun deleteByRoleIdAndMenuId(roleId: Long, menuId: Long)

    /**
     * 역할-메뉴 매핑 존재 여부 확인
     */
    fun existsByRoleIdAndMenuId(roleId: Long, menuId: Long): Boolean

    /**
     * 메뉴 ID로 역할-메뉴 매핑 목록 조회 (메뉴 삭제 시 사용)
     */
    fun findByMenuId(menuId: Long): List<RoleMenu>

    /**
     * 역할-메뉴 매핑 저장
     */
    fun save(roleMenu: RoleMenu): RoleMenu
}