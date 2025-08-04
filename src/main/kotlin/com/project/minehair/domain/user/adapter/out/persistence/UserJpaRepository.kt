package com.project.minehair.domain.user.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository : JpaRepository<UserJpaEntity, Long> {

    /**
     * 사용자 ID로 존재 여부 확인
     */
    fun existsByUserIdAndStatus(userId: String, status: Status): Boolean

    /**
     * 이메일로 존재 여부 확인
     */
    fun existsByEmailAndStatus(email: String, status: Status): Boolean

    /**
     * 전화번호로 존재 여부 확인
     */
    fun existsByPhoneHashAndStatus(phoneHash: String, status: Status): Boolean

    /**
     * 사용자 ID로 사용자 조회
     */
    fun findByUserId(userId: String): UserJpaEntity?

    /**
     * 이메일로 사용자 조회
     */
    fun findByEmail(email: String): UserJpaEntity?
}