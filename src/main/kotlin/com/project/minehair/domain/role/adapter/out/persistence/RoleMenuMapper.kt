package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.domain.role.domain.RoleMenu
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component
import java.time.LocalDateTime

/**
 * RoleMenu 도메인 ↔ JPA 엔티티 매퍼
 * 도메인 객체와 영속성 계층 객체 간의 변환 담당
 */
@Component
class RoleMenuMapper {

    /**
     * JPA 엔티티를 도메인 객체로 변환
     * @param entity RoleMenuJpaEntity
     * @return RoleMenu 도메인 객체
     */
    fun toDomain(entity: RoleMenuJpaEntity): RoleMenu {
        return RoleMenu(
            id = entity.id,
            roleId = entity.roleId,
            menuId = entity.menuId,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    /**
     * 도메인 객체를 JPA 엔티티로 변환
     * @param domain RoleMenu 도메인 객체
     * @return RoleMenuJpaEntity
     */
    fun toEntity(domain: RoleMenu): RoleMenuJpaEntity {
        return RoleMenuJpaEntity(
            id = domain.id,
            roleId = domain.roleId,
            menuId = domain.menuId,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    /**
     * 도메인 객체 리스트를 JPA 엔티티 리스트로 변환
     * @param domains RoleMenu 도메인 객체 리스트
     * @return RoleMenuJpaEntity 리스트
     */
    fun toEntityList(domains: List<RoleMenu>): List<RoleMenuJpaEntity> {
        return domains.map { toEntity(it) }
    }

    /**
     * JPA 엔티티 리스트를 도메인 객체 리스트로 변환
     * @param entities RoleMenuJpaEntity 리스트
     * @return RoleMenu 도메인 객체 리스트
     */
    fun toDomainList(entities: List<RoleMenuJpaEntity>): List<RoleMenu> {
        return entities.map { toDomain(it) }
    }

    /**
     * 새로운 RoleMenu 생성을 위한 헬퍼 메서드
     * @param roleId 역할 ID
     * @param menuId 메뉴 ID
     * @param createdId 생성자 ID
     * @return 새로운 RoleMenu 도메인 객체
     */
    fun createNewRoleMenu(
        roleId: Long,
        menuId: Long,
        createdId: Long
    ): RoleMenu {
        return RoleMenu(
            id = null,
            roleId = roleId,
            menuId = menuId,
            status = Status.active,
            createdId = createdId,
            createdAt = LocalDateTime.now(),
            updatedId = createdId,
            updatedAt = null
        )
    }
}