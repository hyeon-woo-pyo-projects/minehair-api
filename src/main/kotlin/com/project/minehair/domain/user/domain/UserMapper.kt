package com.project.minehair.domain.user.domain

import com.project.minehair.domain.user.adapter.`in`.web.dto.UserCreateRequest
import com.project.minehair.domain.user.adapter.`in`.web.dto.UserResponse
import com.project.minehair.domain.user.adapter.`in`.web.dto.UserUpdateRequest
import com.project.minehair.domain.user.adapter.out.persistence.UserEntity
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class UserMapper {

    /**
     * UserCreateRequest -> Domain User 변환
     */
    fun toDomain(request: UserCreateRequest, encodedPassword: String): User {
        return User(
            roleId = 1L,
            userId = request.userId,
            email = request.email,
            password = encodedPassword,
            name = request.name,
            phone = request.phone,
            userType = request.userType,
            status = Status.active
        )
    }

    /**
     * Domain User -> UserResponse 변환
     */
    fun toResponse(user: User): UserResponse {
        return UserResponse(
            id = user.id!!,
            userId = user.userId,
            email = user.email,
            name = user.name,
            phoneNumber = user.phone,
            userType = user.userType,
            status = user.status,
            createdAt = user.createdAt,
            updatedAt = user.updatedAt
        )
    }

    /**
     * List<User> -> List<UserResponse> 변환
     */
    fun toResponseList(users: List<User>): List<UserResponse> {
        return users.map { toResponse(it) }
    }

    /**
     * UserUpdateRequest -> Domain User 업데이트용 변환
     * 기존 User 객체를 받아서 업데이트된 User 반환
     */
    fun updateToDomain(existingUser: User, request: UserUpdateRequest): User {
        return existingUser.copy(
            email = request.email,
            name = request.name,
            phone = request.phone,
            userType = request.userType
        )
    }

    /**
     * Domain User -> JPA UserEntity 변환
     */
    fun toEntity(user: User): UserEntity {
        return UserEntity(
            id = user.id,
            roleId = user.roleId,
            userId = user.userId,
            email = user.email,
            password = user.password,
            name = user.name,
            phone = user.phone,
            userType = user.userType,
            status = user.status
        )
    }

    /**
     * JPA UserEntity -> Domain User 변환
     */
    fun toDomain(entity: UserEntity): User {
        return User(
            id = entity.id,
            roleId = entity.roleId,
            userId = entity.userId,
            email = entity.email,
            password = entity.password,
            name = entity.name,
            phone = entity.phone,
            userType = entity.userType,
            status = entity.status,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }

    /**
     * List<UserEntity> -> List<User> 변환
     */
    fun toDomainList(entities: List<UserEntity>): List<User> {
        return entities.map { toDomain(it) }
    }
}