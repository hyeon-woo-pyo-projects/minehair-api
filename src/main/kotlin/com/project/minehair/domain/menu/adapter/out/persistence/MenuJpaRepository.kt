package com.project.minehair.domain.menu.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

// MenuJpaRepository.kt - Spring Data JPA Repository
@Repository
interface MenuJpaRepository : JpaRepository<MenuJpaEntity, Long> {

    fun findByIdAndStatus(id: Long, status: Status): MenuJpaEntity?

    fun findAllByIdInAndStatus(ids: List<Long>, status: Status): List<MenuJpaEntity>

    // MenuQueryService용 추가 메서드들
    /**
     * 부모 메뉴 ID로 하위 메뉴들을 조회 (정렬 적용)
     */
    fun findByParentIdOrderByOrderNo(parentId: Long): List<MenuJpaEntity>

    /**
     * 활성화된 메뉴들만 조회 (정렬 적용)
     */
    fun findByIsVisibleTrueOrderByOrderNo(): List<MenuJpaEntity>

    /**
     * 경로로 메뉴 조회
     */
    fun findByPath(path: String): MenuJpaEntity?

    fun findTopByOrderByOrderNoDesc(): MenuJpaEntity?
}