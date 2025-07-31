package com.project.minehair.domain.user.adapter.`in`.query

import com.project.minehair.domain.user.domain.UserMapper
import org.springframework.stereotype.Component

@Component
class UserQueryAdapter(
    private val userQueryService: UserQueryService,
    private val userMapper: UserMapper
) {
}