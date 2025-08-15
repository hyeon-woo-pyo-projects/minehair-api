package com.project.minehair.domain.menu.application.port.out

import com.project.minehair.domain.menu.domain.Menu

// MenuPersistencePort.kt - 출력 포트
interface MenuPersistencePort {
    // 기존 CRUD 메서드들
    fun save(menu: Menu): Menu
    fun findById(id: Long): Menu?
    fun findAll(): List<Menu>
    fun deleteById(id: Long)

    // MenuQueryService용 추가 메서드들
    /**
     * 메뉴 ID 목록으로 메뉴들을 조회
     * @param ids 조회할 메뉴 ID 목록
     * @return 메뉴 엔티티 목록
     */
    fun findByIds(ids: List<Long>): List<Menu>

    /**
     * 부모 메뉴 ID로 하위 메뉴들을 조회
     * @param parentId 부모 메뉴 ID
     * @return 하위 메뉴 엔티티 목록
     */
    fun findByParentId(parentId: Long): List<Menu>

    /**
     * 활성화된 메뉴들만 조회 (선택적)
     * @return 활성화된 메뉴 엔티티 목록
     */
    fun findActiveMenus(): List<Menu>

    /**
     * 특정 경로로 메뉴 조회 (선택적)
     * @param path 메뉴 경로
     * @return 메뉴 엔티티 (없으면 null)
     */
    fun findByPath(path: String): Menu?

    fun findMaxOrderNo(): Int? // 최대 정렬 번호 조회

    /**
     * 메뉴 목록 업데이트
     */
    fun updateAll(menus: List<Menu>): List<Menu>
}