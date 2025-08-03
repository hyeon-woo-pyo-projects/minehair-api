package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.domain.role.domain.Role
import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import com.project.minehair.global.domain.inter.InterDomainUserInfo
import org.springframework.stereotype.Component

@Component
class RoleMapper {

    /**
     * JPA 엔티티를 도메인 객체로 변환
     */
    fun toDomain(entity: RoleJpaEntity): Role {
        return Role(
            id = entity.id,
            code = entity.code,
            name = entity.name,
            description = entity.description,
            priority = entity.priority,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    /**
     * 도메인 객체를 JPA 엔티티로 변환
     */
    fun toEntity(domain: Role): RoleJpaEntity {
        return RoleJpaEntity(
            id = domain.id,
            code = domain.code,
            name = domain.name,
            description = domain.description,
            priority = domain.priority,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    fun toInterDomainRoleInfo(role: Role): InterDomainRoleInfo {
        return InterDomainRoleInfo(
            id = role.id!!,
            code = role.code,
            name = role.name
        )
    }
}