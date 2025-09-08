package com.project.minehair.domain.user.adapter.out.persistence

import com.project.minehair.domain.user.application.port.out.UserPersistencePort
import com.project.minehair.domain.user.domain.User
import com.project.minehair.domain.user.domain.UserMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.enums.Status
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.utils.CryptoUtil
import org.springframework.stereotype.Component

@Component
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository,
    private val userMapper: UserMapper,
    private val cryptoUtil: CryptoUtil
) : UserPersistencePort {

    override fun existsByUserId(userId: String): Boolean {
        return userJpaRepository.existsByUserIdAndStatus(userId, Status.active)
    }

    override fun existsByEmail(email: String): Boolean {
        return userJpaRepository.existsByEmailAndStatus(email, Status.active)
    }

    override fun existsByPhone(phone: String): Boolean {
        val phoneHash = cryptoUtil.hashForSearch(phone)
        return userJpaRepository.existsByPhoneHashAndStatus(phoneHash, Status.active)
    }

    override fun save(user: User): User {
        val userEntity = userMapper.toEntity(user)
        return userMapper.toDomain(userJpaRepository.save(userEntity))
    }

    override fun findUserByUserId(userId: String): User {
        val userEntity = userJpaRepository.findByUserIdAndStatus(userId, Status.active)
            ?: throw IllegalArgumentException("User with userId $userId not found")
        return userMapper.toDomain(userEntity)
    }

    override fun findById(id: Long): User {
        val userEntity = userJpaRepository.findByIdAndStatus(id, Status.active)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "회원 정보가 없습니다.")
        return userMapper.toDomain(userEntity)
    }

    override fun findByNameAndPhoneHash(name: String, phoneHash: String): User {
        val userEntity = userJpaRepository.findByNameAndPhoneHashAndStatus(name, phoneHash, Status.active)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "회원 정보가 없습니다.")
        return userMapper.toDomain(userEntity)
    }
}