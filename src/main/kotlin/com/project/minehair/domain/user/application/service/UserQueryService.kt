package com.project.minehair.domain.user.application.service

import com.project.minehair.domain.user.application.port.`in`.UserQueryUseCase
import com.project.minehair.domain.user.application.port.out.persistence.UserPersistencePort
import com.project.minehair.domain.user.domain.User

class UserQueryService(
    private val userPersistencePort: UserPersistencePort
): UserQueryUseCase {

    // userId로 user조회
    override fun findUserByUserId(userId: String): User {
        return userPersistencePort.findUserByUserId(userId)
            ?: throw IllegalArgumentException("User with userId $userId not found")
    }
}