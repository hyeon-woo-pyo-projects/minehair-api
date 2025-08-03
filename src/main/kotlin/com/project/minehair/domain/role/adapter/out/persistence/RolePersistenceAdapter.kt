package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.domain.role.application.port.out.persistence.RolePersistencePort
import com.project.minehair.domain.role.domain.Role
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
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

    override fun findRoleById(id: Long): Role {
        return roleJpaRepository.findById(id)
            .map { roleEntity ->
                roleMapper.toDomain(roleEntity)
            }
            .orElseThrow { BusinessException(ErrorCode.NOT_FOUND) }
    }

    override fun findRoleByCode(code: String): Role {
        return roleJpaRepository.findByCode(code)
            ?.let { roleEntity ->
                roleMapper.toDomain(roleEntity)
            } ?: throw BusinessException(ErrorCode.NOT_FOUND)
    }

}