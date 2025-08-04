package com.project.minehair.domain.user.adapter.out.persistence

import com.project.minehair.domain.user.application.port.out.UserPersistencePort
import com.project.minehair.domain.user.domain.User
import com.project.minehair.domain.user.domain.UserMapper
import com.project.minehair.global.utils.CryptoUtil
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper,
    private val cryptoUtil: CryptoUtil
) : UserPersistencePort {

    override fun existsByUserId(userId: String): Boolean {
        return userJpaRepository.existsByUserId(userId)
    }

    override fun existsByEmail(email: String): Boolean {
        return userJpaRepository.existsByEmail(email)
    }

    override fun existsByPhone(phone: String): Boolean {
        val phoneHash = cryptoUtil.hashForSearch(phone)
        return userJpaRepository.existsByPhoneHash(phoneHash)
    }

    override fun save(user: User) {
        val userEntity = userMapper.toEntity(user)
        userJpaRepository.save(userEntity)
    }

    override fun findUserByUserId(userId: String): User {
        val userEntity = userJpaRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("User with userId $userId not found")
        return userMapper.toDomain(userEntity)
    }
}