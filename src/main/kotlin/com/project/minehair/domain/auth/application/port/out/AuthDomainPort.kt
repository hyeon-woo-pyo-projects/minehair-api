package com.project.minehair.domain.auth.application.port.out

import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import com.project.minehair.global.domain.inter.InterDomainUserInfo

interface AuthDomainPort {

    fun getUserByUserId(userId: String): InterDomainUserInfo?

    fun getRoleById(roleId: Long): InterDomainRoleInfo?

}