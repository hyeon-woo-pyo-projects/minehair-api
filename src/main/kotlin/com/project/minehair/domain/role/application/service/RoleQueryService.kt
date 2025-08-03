package com.project.minehair.domain.role.application.service

import com.project.minehair.domain.role.application.port.`in`.RoleQueryUseCase
import com.project.minehair.domain.role.application.port.out.persistence.RolePersistencePort
import com.project.minehair.domain.role.domain.Role
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class RoleQueryService (
    private val rolePersistencePort: RolePersistencePort
) : RoleQueryUseCase {

    // id로 role 조회
    override fun findRoleById(id: Long): Role {
        return rolePersistencePort.findRoleById(id)
    }

    override fun findRoleByCode(roleCode: String): Role {
        return rolePersistencePort.findRoleByCode(roleCode)
    }
}