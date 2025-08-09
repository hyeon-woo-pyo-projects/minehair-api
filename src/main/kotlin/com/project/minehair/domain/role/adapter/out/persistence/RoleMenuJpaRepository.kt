package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleMenuJpaRepository : JpaRepository<RoleMenuJpaEntity, Long> {

    /**
     * 역할 ID로 역할-메뉴 매핑 목록 조회
     * @param roleId 역할 ID
     * @return 역할-메뉴 매핑 엔티티 목록
     */
    fun findByRoleIdAndStatus(roleId: Long, status: Status): List<RoleMenuJpaEntity>

    /**
     * 역할 ID와 메뉴 ID로 특정 매핑 조회
     * @param roleId 역할 ID
     * @param menuId 메뉴 ID
     * @return 역할-메뉴 매핑 엔티티 (없으면 null)
     */
    fun findByRoleIdAndMenuId(roleId: Long, menuId: Long): RoleMenuJpaEntity?

    /**
     * 역할 ID와 메뉴 ID로 매핑 존재 여부 확인
     * @param roleId 역할 ID
     * @param menuId 메뉴 ID
     * @return 존재 여부
     */
    fun existsByRoleIdAndMenuId(roleId: Long, menuId: Long): Boolean

    /**
     * 메뉴 ID로 역할-메뉴 매핑 목록 조회
     * @param menuId 메뉴 ID
     * @return 역할-메뉴 매핑 엔티티 목록
     */
    fun findByMenuId(menuId: Long): List<RoleMenuJpaEntity>

    /**
     * 역할의 모든 메뉴 매핑 삭제
     * @param roleId 역할 ID
     */
    fun deleteByRoleId(roleId: Long)

    /**
     * 특정 역할-메뉴 매핑 삭제
     * @param roleId 역할 ID
     * @param menuId 메뉴 ID
     */
    fun deleteByRoleIdAndMenuId(roleId: Long, menuId: Long)

    /**
     * 활성 상태인 역할-메뉴 매핑 조회
     * @param roleId 역할 ID
     * @param status 상태
     * @return 해당 상태의 역할-메뉴 매핑 엔티티 목록
     */
    fun findByRoleIdAndStatus(roleId: Long, status: String): List<RoleMenuJpaEntity>

    /**
     * 역할 ID 목록으로 모든 역할-메뉴 매핑 조회
     * @param roleIds 역할 ID 목록
     * @return 역할-메뉴 매핑 엔티티 목록
     */
    fun findByRoleIdIn(roleIds: List<Long>): List<RoleMenuJpaEntity>
}