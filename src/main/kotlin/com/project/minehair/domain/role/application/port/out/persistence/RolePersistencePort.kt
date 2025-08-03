package com.project.minehair.domain.role.application.port.out.persistence

import com.project.minehair.domain.role.domain.Role

interface RolePersistencePort {

    /**
     * 모든 역할 리스트 조회
     */
    fun findAllRoles(): List<Role>

    /**
     * 역할 ID로 역할 조회
     */
    fun findRoleById(id: Long): Role

    /**
     * 역할 코드로 역할 조회
     */
    fun findRoleByCode(code: String): Role
}