package com.project.minehair.domain.role.application.port.`in`

import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleResponse

interface RoleUseCase {

    /**
     * 전체 역할 조회
     */
    fun getAllRoles(): List<RoleResponse>

}