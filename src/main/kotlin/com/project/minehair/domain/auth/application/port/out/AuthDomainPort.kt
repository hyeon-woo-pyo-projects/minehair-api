package com.project.minehair.domain.auth.application.port.out

import com.project.minehair.global.domain.inter.InterDomainUserInfo

interface AuthDomainPort {

    fun getUserByUserId(userId: String): InterDomainUserInfo?

}