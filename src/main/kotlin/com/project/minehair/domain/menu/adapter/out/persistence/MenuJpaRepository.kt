package com.project.minehair.domain.menu.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// MenuJpaRepository.kt - Spring Data JPA Repository
@Repository
interface MenuJpaRepository : JpaRepository<MenuJpaEntity, Long> {
    fun findByStatus(status: String): List<MenuJpaEntity>
    fun findByIsVisible(visible: Boolean): List<MenuJpaEntity>
    fun findByParentId(parentId: Long): List<MenuJpaEntity>

    // MenuQueryService용 추가 메서드들
    /**
     * 부모 메뉴 ID로 하위 메뉴들을 조회 (정렬 적용)
     * @param parentId 부모 메뉴 ID
     * @return 하위 메뉴 엔티티 목록 (orderNo 순 정렬)
     */
    fun findByParentIdOrderByOrderNo(parentId: Long): List<MenuJpaEntity>

    /**
     * 활성화된 메뉴들만 조회 (정렬 적용)
     * @return 활성화된 메뉴 엔티티 목록 (orderNo 순 정렬)
     */
    fun findByIsVisibleTrueOrderByOrderNo(): List<MenuJpaEntity>

    /**
     * 경로로 메뉴 조회
     * @param path 메뉴 경로
     * @return 메뉴 엔티티 (없으면 null)
     */
    fun findByPath(path: String): MenuJpaEntity?

    fun findTopByOrderByOrderNoDesc(): MenuJpaEntity?
}