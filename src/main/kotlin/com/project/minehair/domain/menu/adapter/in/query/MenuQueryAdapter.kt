package com.project.minehair.domain.menu.adapter.`in`.query

import com.project.minehair.domain.menu.adapter.out.persistence.MenuMapper
import com.project.minehair.domain.menu.application.service.MenuQueryService
import com.project.minehair.global.domain.inter.InterDomainMenuInfo
import org.springframework.stereotype.Component

/**
 * Menu 도메인의 외부 노출용 조회 어댑터
 * 다른 도메인에서 Menu 정보를 조회할 때 사용하는 Public Interface
 * MenuQueryService를 통해 올바른 계층 구조 유지
 */
@Component
class MenuQueryAdapter(
    private val menuQueryService: MenuQueryService,
    private val menuMapper: MenuMapper
) {

    /**
     * 메뉴 ID 목록으로 메뉴들을 조회
     * @param menuIds 조회할 메뉴 ID 목록
     * @return 메뉴 엔티티 목록
     */
    fun findMenusByIds(menuIds: List<Long>): List<InterDomainMenuInfo> {
        if (menuIds.isEmpty()) {
            return emptyList()
        }
        val menus = menuQueryService.findMenusByIds(menuIds)
        return menuMapper.toInterDomainInfoList(menus)
    }

    /**
     * 단일 메뉴 ID로 메뉴 조회
     * @param menuId 조회할 메뉴 ID
     * @return 메뉴 엔티티
     */
    fun findMenuById(menuId: Long): InterDomainMenuInfo {
        val menu = menuQueryService.findMenuById(menuId)
        return menuMapper.toInterDomainInfo(menu)
    }

    /**
     * 활성화된 메뉴들만 조회
     * @param menuIds 조회할 메뉴 ID 목록
     * @return 활성화된 메뉴 엔티티 목록
     */
    fun findActiveMenusByIds(menuIds: List<Long>): List<InterDomainMenuInfo> {
        if (menuIds.isEmpty()) {
            return emptyList()
        }
        val activeMenus = menuQueryService.findActiveMenusByIds(menuIds)
        return menuMapper.toInterDomainInfoList(activeMenus)
    }

    /**
     * 부모 메뉴 ID로 하위 메뉴들 조회
     * @param parentMenuId 부모 메뉴 ID
     * @return 하위 메뉴 엔티티 목록
     */
    fun findChildMenus(parentMenuId: Long): List<InterDomainMenuInfo> {
        val childMenus = menuQueryService.findChildMenus(parentMenuId)
        return menuMapper.toInterDomainInfoList(childMenus)
    }
}