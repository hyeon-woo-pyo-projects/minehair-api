package com.project.minehair.domain.user.application.port.out

import com.project.minehair.global.domain.inter.InterDomainRoleInfo

interface UserDomainPort {

    fun getRoleByCode(roleCode: String): InterDomainRoleInfo?

}
