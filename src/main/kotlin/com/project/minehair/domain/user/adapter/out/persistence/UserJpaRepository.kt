package com.project.minehair.domain.user.adapter.out.persistence

import com.project.minehair.domain.user.adapter.out.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserEntity, Long> {

    /**
     * 사용자 ID로 존재 여부 확인
     */
    fun existsByUserId(userId: String): Boolean

    /**
     * 이메일로 존재 여부 확인
     */
    fun existsByEmail(email: String): Boolean

    /**
     * 사용자 ID로 사용자 조회
     */
    fun findByUserId(userId: String): UserEntity?

    /**
     * 이메일로 사용자 조회
     */
    fun findByEmail(email: String): UserEntity?
}