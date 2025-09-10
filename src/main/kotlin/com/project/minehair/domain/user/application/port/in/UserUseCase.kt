package com.project.minehair.domain.user.application.port.`in`

import com.project.minehair.domain.user.adapter.`in`.web.dto.*

interface UserUseCase {
    /**
     * 사용자 생성 (회원가입)
     */
    fun createUser(request: CreateUserRequest): UserResponse

    /**
     * 사용자 조회 (마이페이지 - 상세 정보)
     */
    fun getUserDetailsMyPage(): UserResponse

//    /**
//     * 사용자 조회 (상세 정보)
//     */
//    fun getUserDetails(id: Long): UserResponse

    /**
     * 아이디 조회 (이름, 전화번호로)
     */
    fun getUserId(request: UserIdRequest): String?

    /**
     * 사용자 조회 (상세 정보) - 비밀번호 찾기 전
     */
    fun getUserDetails(request: UserDetailsRequest): UserResponse

    /**
     * 비밀번호 변경 (휴대전화번호 인증 후)
     */
    fun updatePassword(id: Long, request: UpdatePasswordRequest): UserResponse

    /**
     * 회원 정보 수정
     */
    fun updateUser(id: Long, request: UpdateUserRequest): UserResponse

    /**
     * 회원 탈퇴
     */
    fun deleteUser(): UserResponse

}