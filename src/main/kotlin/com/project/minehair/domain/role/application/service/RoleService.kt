package com.project.minehair.domain.role.application.service

import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleResponse
import com.project.minehair.domain.role.application.port.`in`.RoleUseCase
import com.project.minehair.domain.role.application.port.out.persistence.RolePersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class RoleService(
    private val rolePersistencePort: RolePersistencePort,
) : RoleUseCase {

    override fun getAllRoles(): List<RoleResponse> {
        // 1. 전체 역할 조회
        val roles = rolePersistencePort.findAllRoles()

        // 2. 응답 DTO로 변환
        return roles.map { role ->
            RoleResponse(
                id = role.id,
                name = role.name,
                code = role.code,
            )
        }
    }
}

