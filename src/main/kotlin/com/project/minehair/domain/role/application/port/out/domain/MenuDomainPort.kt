package com.project.minehair.domain.role.application.port.out.domain

import com.project.minehair.global.domain.inter.InterDomainMenuInfo
import com.project.minehair.global.domain.inter.InterDomainRoleInfo

interface MenuDomainPort {
    /**
     * 메뉴 ID 목록으로 메뉴 정보들을 조회
     */
    fun getMenusByIds(menuIds: List<Long>): List<InterDomainMenuInfo>

    /**
     * 단일 메뉴 ID로 메뉴 정보 조회
     */
    fun getMenuById(menuId: Long): InterDomainMenuInfo?

    /**
     * Role code로 역할 정보 조회
     */
    fun getRoleByCode(roleCode: String): InterDomainRoleInfo?

}