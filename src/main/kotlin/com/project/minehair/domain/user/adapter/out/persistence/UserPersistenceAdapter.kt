package com.project.minehair.domain.user.adapter.out.persistence

import com.project.minehair.domain.user.application.port.out.persistence.UserPersistencePort
import com.project.minehair.domain.user.domain.User
import com.project.minehair.domain.user.domain.UserMapper
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper
) : UserPersistencePort {

    override fun existsByUserId(userId: String): Boolean {
        return userJpaRepository.existsByUserId(userId)
    }

    override fun existsByEmail(email: String): Boolean {
        return userJpaRepository.existsByEmail(email)
    }

    override fun save(user: User) {
        val userEntity = userMapper.toEntity(user)
        userJpaRepository.save(userEntity)
    }
}