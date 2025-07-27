package com.project.minehair.domain.user.application.port.`in`

import com.project.minehair.domain.user.adapter.`in`.web.dto.UserCreateRequest

interface UserUseCase {
    /**
     * 사용자 생성 (회원가입)
     */
    fun createUser(request: UserCreateRequest)

//    /**
//     * 사용자 조회 (ID로)
//     */
//    fun getUserById(id: Long): UserResponse
//
//    /**
//     * 전체 사용자 목록 조회
//     */
//    fun getAllUsers(page: Int, size: Int, userType: String?, status: String?): List<UserResponse>
//
//    /**
//     * 사용자 정보 수정
//     */
//    fun updateUser(id: Long, request: UserUpdateRequest): UserResponse
//
//    /**
//     * 사용자 상태 변경 (활성화/비활성화/정지)
//     */
//    fun changeUserStatus(id: Long, status: String): UserResponse
//
//    /**
//     * 사용자 삭제 (논리적 삭제)
//     */
//    fun deleteUser(id: Long)
//
//    /**
//     * 비밀번호 변경
//     */
//    fun changePassword(id: Long, oldPassword: String, newPassword: String)
//
//    /**
//     * 사용자 ID 중복 확인
//     */
//    fun checkUserIdDuplicate(userId: String): Boolean
}