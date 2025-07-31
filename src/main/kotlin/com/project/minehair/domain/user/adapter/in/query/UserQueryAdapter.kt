package com.project.minehair.domain.user.adapter.`in`.query

import com.project.minehair.domain.user.application.service.UserQueryService
import com.project.minehair.domain.user.domain.UserMapper
import com.project.minehair.global.domain.inter.InterDomainUserInfo
import org.springframework.stereotype.Component

@Component
class UserQueryAdapter(
    private val userQueryService: UserQueryService,
    private val userMapper: UserMapper
) {

    fun getUserByUserId(userId: String): InterDomainUserInfo? {
        val user = userQueryService.findUserByUserId(userId)
        return userMapper.toInterDomainUserInfo(user)
    }

}