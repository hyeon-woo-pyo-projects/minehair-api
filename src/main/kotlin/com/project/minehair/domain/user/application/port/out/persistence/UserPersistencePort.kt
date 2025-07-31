package com.project.minehair.domain.user.application.port.out.persistence

import com.project.minehair.domain.user.domain.User

interface UserPersistencePort {

    /**
     * 사용자 ID로 사용자 존재 여부 확인
     */
    fun existsByUserId(userId: String): Boolean

    /**
     * 이메일로 사용자 존재 여부 확인
     */
    fun existsByEmail(email: String): Boolean

    /**
     * 사용자 저장
     */
    fun save(user: User)


    fun findUserByUserId(userId: String):User

}