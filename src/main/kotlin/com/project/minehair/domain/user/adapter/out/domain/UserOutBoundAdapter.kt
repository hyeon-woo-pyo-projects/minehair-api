package com.project.minehair.domain.user.adapter.out.domain

import com.project.minehair.domain.role.adapter.`in`.query.RoleQueryAdapter
import com.project.minehair.domain.user.application.port.out.UserDomainPort
import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import org.springframework.stereotype.Component

@Component
class UserOutBoundAdapter(
    private val roleQueryAdapter: RoleQueryAdapter
): UserDomainPort {

    override fun getRoleByCode(roleCode: String): InterDomainRoleInfo? {
        return roleQueryAdapter.getRoleByCode(roleCode)?.let { role ->
            InterDomainRoleInfo(
                id = role.id,
                code = role.code,
                name = role.name,
            )
        }
    }

}