package com.project.minehair.domain.user.application.service

import com.project.minehair.domain.user.adapter.`in`.web.dto.*
import com.project.minehair.domain.user.application.port.`in`.UserUseCase
import com.project.minehair.domain.user.application.port.out.UserDomainPort
import com.project.minehair.domain.user.application.port.out.UserPersistencePort
import com.project.minehair.domain.user.domain.User
import com.project.minehair.domain.user.domain.UserMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.enums.Status
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.filter.context.JwtTokenContext
import com.project.minehair.global.utils.CryptoUtil
import com.project.minehair.global.utils.RedisUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@Transactional(readOnly = true)
class UserService(
    private val userPersistencePort: UserPersistencePort,
    private val userDomainPort: UserDomainPort,
    private val passwordEncoder: PasswordEncoder,
    private val cryptoUtil: CryptoUtil,
    private val userMapper: UserMapper,
    private val redisUtil: RedisUtil
) : UserUseCase {

    @Transactional
    override fun createUser(request: CreateUserRequest): UserResponse {
        // 사용자 ID 중복 확인
        if (userPersistencePort.existsByUserId(request.userId)) {
            throw BusinessException(ErrorCode.DUPLICATE_USERID)
        }

        // 비밀번호 확인
        if (request.password != request.confirmPassword) {
            throw BusinessException(ErrorCode.PASSWORD_MISMATCH)
        }

        // 이메일 중복 확인
        if (userPersistencePort.existsByEmail(request.email)) {
            throw BusinessException(ErrorCode.DUPLICATE_EMAIL)
        }

        // 전화번호 중복 확인
        if (userPersistencePort.existsByPhone(request.phone)) {
            throw BusinessException(ErrorCode.DUPLICATE_PHONE)
        }

        // ROLE_USER 조회
        val role = userDomainPort.getRoleByCode("ROLE_USER")
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "role")

        val user = User(
            id = null,
            roleId = role.id,
            userId = request.userId,
            email = request.email,
            password = passwordEncoder.encode(request.password),
            name = request.name,
            phone = cryptoUtil.encrypt(request.phone),
            phoneHash = cryptoUtil.hashForSearch(request.phone),
            birthDate = request.birthDate,
            status = Status.active,
            createdId = 1L,
            createdAt = LocalDateTime.now(),
            updatedId = 1L,
            updatedAt = null
        )
        return userMapper.toResponse(userPersistencePort.save(user))
    }

    override fun getUserDetailsMyPage(): UserResponse {
        val user = userPersistencePort.findById(JwtTokenContext.getId())
        val decryptPhoneUser = user.decryptPhone(cryptoUtil.decrypt(user.phone))
        return userMapper.toResponse(decryptPhoneUser)
    }

//    override fun getUserDetails(id: Long): UserResponse {
//        val user = userPersistencePort.findById(id)
//        return userMapper.toResponse(user)
//    }

    override fun getUserId(request: UserIdRequest): String? {
        val phoneHash = cryptoUtil.hashForSearch(request.phone)
        val user = userPersistencePort.findByNameAndPhoneHash(request.name, phoneHash)
        return user.userId
    }

    override fun getUserDetails(request: UserDetailsRequest): UserResponse {
        val phoneHash = cryptoUtil.hashForSearch(request.phone)
        val user = userPersistencePort.findByNameAndPhoneHash(request.name, phoneHash)
        if (user.userId != request.userId) {
            throw BusinessException(ErrorCode.NOT_FOUND, "user")
        }
        val decryptPhoneUser = user.decryptPhone(cryptoUtil.decrypt(user.phone))
        return userMapper.toResponse(decryptPhoneUser)
    }

    @Transactional
    override fun updatePassword(id: Long, request: UpdatePasswordRequest): UserResponse {
        if (request.newPassword != request.confirmPassword) {
            throw BusinessException(ErrorCode.PASSWORD_MISMATCH)
        }
        val user = userPersistencePort.findById(id)
        val updatedUser = user.changePassword(passwordEncoder.encode(request.newPassword))
        return userMapper.toResponse(userPersistencePort.save(updatedUser))
    }

    @Transactional
    override fun updateUser(request: UpdateUserRequest): UserResponse {
        val user = userPersistencePort.findById(JwtTokenContext.getId())

        // 이메일 중복 확인
        if (user.email != request.email && userPersistencePort.existsByEmail(request.email)) {
            throw BusinessException(ErrorCode.DUPLICATE_EMAIL)
        }

        // 전화번호 중복 확인
        if (cryptoUtil.decrypt(user.phone) != request.phone && userPersistencePort.existsByPhone(request.phone)) {
            throw BusinessException(ErrorCode.DUPLICATE_PHONE)
        }

        val updatedUser = user.update(
            email = request.email,
            name = request.name,
            phone = cryptoUtil.encrypt(request.phone),
            phoneHash = cryptoUtil.hashForSearch(request.phone),
            birthDate = request.birthDate
        )
        return userMapper.toResponse(userPersistencePort.save(updatedUser))
    }

    @Transactional
    override fun deleteUser(): UserResponse {
        val user = userPersistencePort.findById(JwtTokenContext.getId())
        val deletedUser = userPersistencePort.save(user.delete())
        // redis token 삭제
        redisUtil.delete("user:${JwtTokenContext.getUserId()}:accessToken")
        return userMapper.toResponse(deletedUser)
    }


}