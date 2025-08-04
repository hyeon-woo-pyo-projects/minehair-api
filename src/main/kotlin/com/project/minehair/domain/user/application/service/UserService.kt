package com.project.minehair.domain.user.application.service

import com.project.minehair.domain.user.adapter.`in`.web.dto.UserCreateRequest
import com.project.minehair.domain.user.application.port.`in`.UserUseCase
import com.project.minehair.domain.user.application.port.out.UserDomainPort
import com.project.minehair.domain.user.application.port.out.UserPersistencePort
import com.project.minehair.domain.user.domain.User
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.enums.Status
import com.project.minehair.global.exception.BusinessException
import com.project.minehair.global.utils.CryptoUtil
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
    private val cryptoUtil: CryptoUtil
) : UserUseCase {

    @Transactional
    override fun createUser(request: UserCreateRequest) {
        // 사용자 ID 중복 확인
        if (userPersistencePort.existsByUserId(request.userId)) {
            throw BusinessException(ErrorCode.DUPLICATE_USERID)
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
            status = Status.active,
            createdId = 1L,
            createdAt = LocalDateTime.now(),
            updatedId = 1L,
            updatedAt = null
        )
        userPersistencePort.save(user)
    }

//    override fun getUserById(id: Long): UserResponse {
//        val user = userPort.findById(id)
//            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)
//
//        return UserResponse.from(user)
//    }
//
//    override fun getAllUsers(page: Int, size: Int, userType: String?, status: String?): List<UserResponse> {
//        val users = userPort.findAll(page, size, userType, status)
//        return users.map { UserResponse.from(it) }
//    }
//
//    @Transactional
//    override fun updateUser(id: Long, request: UserUpdateRequest): UserResponse {
//        val user = userPort.findById(id)
//            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)
//
//        // 이메일 변경 시 중복 확인
//        if (request.email != user.email && userPort.existsByEmail(request.email)) {
//            throw BusinessException(ErrorCode.DUPLICATE_EMAIL)
//        }
//
//        val updatedUser = user.update(
//            email = request.email,
//            name = request.name,
//            phoneNumber = request.phoneNumber,
//            userType = request.userType
//        )
//
//        val savedUser = userPort.save(updatedUser)
//        return UserResponse.from(savedUser)
//    }
//
//    @Transactional
//    override fun changeUserStatus(id: Long, status: String): UserResponse {
//        val user = userPort.findById(id)
//            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)
//
//        val userStatus = try {
//            UserStatus.valueOf(status.uppercase())
//        } catch (e: IllegalArgumentException) {
//            throw BusinessException(ErrorCode.INVALID_USER_STATUS)
//        }
//
//        val updatedUser = user.changeStatus(userStatus)
//        val savedUser = userPort.save(updatedUser)
//
//        return UserResponse.from(savedUser)
//    }
//
//    @Transactional
//    override fun deleteUser(id: Long) {
//        val user = userPort.findById(id)
//            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)
//
//        val deletedUser = user.delete()
//        userPort.save(deletedUser)
//    }
//
//    @Transactional
//    override fun changePassword(id: Long, oldPassword: String, newPassword: String) {
//        val user = userPort.findById(id)
//            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)
//
//        // 기존 비밀번호 확인
//        if (!passwordEncoder.matches(oldPassword, user.password)) {
//            throw BusinessException(ErrorCode.INVALID_PASSWORD)
//        }
//
//        val encodedNewPassword = passwordEncoder.encode(newPassword)
//        val updatedUser = user.changePassword(encodedNewPassword)
//
//        userPort.save(updatedUser)
//    }
//
//    override fun checkUserIdDuplicate(userId: String): Boolean {
//        return userPort.existsByUserId(userId)
//    }
}