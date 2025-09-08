package com.project.minehair.domain.user.domain

import com.project.minehair.domain.user.adapter.`in`.web.dto.UserResponse
import com.project.minehair.domain.user.adapter.`in`.web.dto.UpdateUserRequest
import com.project.minehair.domain.user.adapter.out.persistence.UserJpaEntity
import com.project.minehair.global.domain.inter.InterDomainUserInfo
import org.springframework.stereotype.Component

@Component
class UserMapper {

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
    fun updateToDomain(existingUser: User, request: UpdateUserRequest): User {
        return existingUser.copy(
            email = request.email,
            name = request.name,
            phone = request.phone,
        )
    }

    /**
     * Domain User -> JPA UserEntity 변환
     */
    fun toEntity(user: User): UserJpaEntity {
        return UserJpaEntity(
            id = user.id,
            roleId = user.roleId,
            userId = user.userId,
            email = user.email,
            password = user.password,
            name = user.name,
            phone = user.phone,
            phoneHash = user.phoneHash,
            status = user.status
        )
    }

    /**
     * JPA UserEntity -> Domain User 변환
     */
    fun toDomain(entity: UserJpaEntity): User {
        return User(
            id = entity.id,
            roleId = entity.roleId,
            userId = entity.userId,
            email = entity.email,
            password = entity.password,
            name = entity.name,
            phone = entity.phone,
            phoneHash = entity.phoneHash,
            status = entity.status,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }

    /**
     * List<UserEntity> -> List<User> 변환
     */
    fun toDomainList(entities: List<UserJpaEntity>): List<User> {
        return entities.map { toDomain(it) }
    }

    fun toInterDomainUserInfo(user: User): InterDomainUserInfo {
        return InterDomainUserInfo(
            id = user.id!!,
            roleId = user.roleId,
            userId = user.userId,
            password = user.password,
        )
    }
}