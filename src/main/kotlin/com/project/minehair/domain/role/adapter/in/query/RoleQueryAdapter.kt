package com.project.minehair.domain.role.adapter.`in`.query

import com.project.minehair.domain.role.adapter.out.persistence.RoleMapper
import com.project.minehair.domain.role.application.service.RoleQueryService
import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import org.springframework.stereotype.Component

@Component
class RoleQueryAdapter(
    private val roleQueryService: RoleQueryService,
    private val roleMapper: RoleMapper
) {
    fun getRoleById(id: Long): InterDomainRoleInfo? {
        val role = roleQueryService.findRoleById(id)
        return roleMapper.toInterDomainRoleInfo(role)
    }
}