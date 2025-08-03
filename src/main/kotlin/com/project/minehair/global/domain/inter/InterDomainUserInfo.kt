package com.project.minehair.global.domain.inter

import com.project.minehair.domain.user.domain.UserType

data class InterDomainUserInfo(
    val id: Long,
    val roleId: Long,
    val userId: String,
    val password: String,
    val userType: UserType
)

