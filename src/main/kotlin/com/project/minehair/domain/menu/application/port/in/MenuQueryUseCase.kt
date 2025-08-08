package com.project.minehair.domain.menu.application.port.`in`

import com.project.minehair.domain.menu.domain.Menu

/**
 * Menu 도메인 간 통신용 조회 UseCase
 * 외부 도메인에서 Menu Entity 조회 시 사용하는 인터페이스
 */
interface MenuQueryUseCase {

    /**
     * 메뉴 ID 목록으로 메뉴들을 조회
     * @param menuIds 조회할 메뉴 ID 목록
     * @return 메뉴 엔티티 목록
     */
    fun findMenusByIds(menuIds: List<Long>): List<Menu>

    /**
     * 단일 메뉴 ID로 메뉴 조회
     * @param menuId 조회할 메뉴 ID
     * @return 메뉴 엔티티
     * @throws IllegalArgumentException 메뉴가 존재하지 않는 경우
     */
    fun findMenuById(menuId: Long): Menu

    /**
     * 활성화된 메뉴들만 조회
     * @param menuIds 조회할 메뉴 ID 목록
     * @return 활성화된 메뉴 엔티티 목록
     */
    fun findActiveMenusByIds(menuIds: List<Long>): List<Menu>

    /**
     * 부모 메뉴 ID로 하위 메뉴들 조회
     * @param parentMenuId 부모 메뉴 ID
     * @return 하위 메뉴 엔티티 목록
     */
    fun findChildMenus(parentMenuId: Long): List<Menu>

    fun findMaxOrderNo(): Int
}