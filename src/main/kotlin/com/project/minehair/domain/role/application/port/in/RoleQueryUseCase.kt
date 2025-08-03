package com.project.minehair.domain.role.application.port.`in`

import com.project.minehair.domain.role.domain.Role

interface RoleQueryUseCase {

    // id로 조회
    fun findRoleById(id: Long): Role

    // code로 조회
    fun findRoleByCode(roleCode: String): Role?

}