package com.project.minehair.domain.auth.adapter.out.domain

import com.project.minehair.domain.auth.application.port.out.AuthDomainPort
import com.project.minehair.global.domain.inter.InterDomainUserInfo
import org.springframework.stereotype.Component

@Component
class AuthOutboundAdapter(
    private val userQueryAdapter: UserQueryAdapter
): AuthDomainPort {

    override fun getUserByUserId(userId: String): InterDomainUserInfo? {
        return userQueryAdapter.findUserByUserId(userId)?.let { user ->
            InterDomainUserInfo(
                id = user.id,
                // 필요한 다른 필드들도 추가 가능
            )
        }
    }
}