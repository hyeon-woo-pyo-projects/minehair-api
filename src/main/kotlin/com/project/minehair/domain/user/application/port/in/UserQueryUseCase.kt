package com.project.minehair.domain.user.application.port.`in`

import com.project.minehair.domain.user.domain.User

interface UserQueryUseCase {

    // userId로 user조회
    fun findUserByUserId(userId: String): User

}