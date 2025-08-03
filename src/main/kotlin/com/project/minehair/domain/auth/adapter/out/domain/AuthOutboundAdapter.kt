package com.project.minehair.domain.auth.adapter.out.domain

import com.project.minehair.domain.auth.application.port.out.AuthDomainPort
import com.project.minehair.domain.role.adapter.`in`.query.RoleQueryAdapter
import com.project.minehair.domain.user.adapter.`in`.query.UserQueryAdapter
import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import com.project.minehair.global.domain.inter.InterDomainUserInfo
import org.springframework.stereotype.Component

@Component
class AuthOutboundAdapter(
    private val userQueryAdapter: UserQueryAdapter,
    private val roleQueryAdapter: RoleQueryAdapter
): AuthDomainPort {

    override fun getUserByUserId(userId: String): InterDomainUserInfo? {
        return userQueryAdapter.getUserByUserId(userId)?.let { user ->
            InterDomainUserInfo(
                id = user.id,
                roleId = user.roleId,
                userId = user.userId,
                password = user.password,
                userType = user.userType
            )
        }
    }

    override fun getRoleById(id: Long): InterDomainRoleInfo? {
        return roleQueryAdapter.getRoleById(id)?.let { role ->
            InterDomainRoleInfo(
                id = role.id,
                code = role.code,
                name = role.name,
            )
        }
    }
}