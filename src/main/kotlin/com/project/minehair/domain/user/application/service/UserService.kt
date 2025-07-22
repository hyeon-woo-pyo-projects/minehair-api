package com.project.minehair.domain.user.application.service

import com.project.minehair.domain.user.adapter.`in`.web.dto.UserCreateRequest
import com.project.minehair.domain.user.application.port.`in`.UserUseCase
import com.project.minehair.domain.user.domain.User
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class UserService(
//    private val userPort: UserPort,
//    private val passwordEncoder: PasswordEncoder
) : UserUseCase {

    @Transactional
    override fun createUser(request: UserCreateRequest) {
        // 사용자 ID 중복 확인
        if (userPort.existsByUserId(request.userId)) {
            throw BusinessException(ErrorCode.DUPLICATE_USER_ID)
        }

        // 이메일 중복 확인
        if (userPort.existsByEmail(request.email)) {
            throw BusinessException(ErrorCode.DUPLICATE_EMAIL)
        }

        val encodedPassword = passwordEncoder.encode(request.password)

        val user = User(
            userId = request.userId,
            email = request.email,
            password = encodedPassword,
            name = request.name,
            phoneNumber = request.phoneNumber,
            userType = request.userType,
            status = Status.active
        )
        userPort.save(user)
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