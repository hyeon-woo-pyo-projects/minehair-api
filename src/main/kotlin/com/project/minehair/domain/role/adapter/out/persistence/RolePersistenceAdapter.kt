package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.domain.role.application.port.out.persistence.RolePersistencePort
import com.project.minehair.domain.role.domain.Role
import org.springframework.stereotype.Component

@Component
class RolePersistenceAdapter(
    private val roleJpaRepository: RoleJpaRepository,
    private val roleMapper: RoleMapper
) : RolePersistencePort {

    override fun findAllRoles(): List<Role> {
        return roleJpaRepository.findAll()
            .map { roleEntity ->
                roleMapper.toDomain(roleEntity)
            }
    }

}