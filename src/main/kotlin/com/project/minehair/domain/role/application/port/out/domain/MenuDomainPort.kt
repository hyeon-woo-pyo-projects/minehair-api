package com.project.minehair.domain.role.application.port.out.domain

import com.project.minehair.global.domain.inter.InterDomainMenuInfo

interface MenuDomainPort {
    /**
     * 메뉴 ID 목록으로 메뉴 정보들을 조회
     * @param menuIds 조회할 메뉴 ID 목록
     * @return 메뉴 정보 목록
     */
    fun getMenusByIds(menuIds: List<Long>): List<InterDomainMenuInfo>

    /**
     * 단일 메뉴 ID로 메뉴 정보 조회
     * @param menuId 조회할 메뉴 ID
     * @return 메뉴 정보 (없으면 null)
     */
    fun getMenuById(menuId: Long): InterDomainMenuInfo?
}