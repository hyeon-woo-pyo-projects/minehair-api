package com.project.minehair.domain.role.application.port.`in`

import com.project.minehair.domain.role.domain.Role

interface RoleQueryUseCase {

    // id
    fun findRoleById(id: Long): Role

}