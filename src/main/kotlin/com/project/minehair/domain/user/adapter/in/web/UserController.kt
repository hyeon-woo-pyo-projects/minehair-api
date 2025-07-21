package com.example.users.adapter.`in`.web

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
//    private val userUseCase: UserUseCase
) {

//    /**
//     * 사용자 생성 (회원가입)
//     */
//    @PostMapping
//    fun createUser(@Valid @RequestBody request: UserCreateRequest): BaseResponse<Nothing?> {
//        val response = userUseCase.createUser(request)
//        return BaseResponse.ok()
//    }

//    /**
//     * 사용자 조회 (ID로)
//     */
//    @GetMapping("/{id}")
//    fun getUserById(@PathVariable id: Long): ResponseEntity<UserResponse> {
//        val response = userUseCase.getUserById(id)
//        return ResponseEntity.ok(response)
//    }
//
//    /**
//     * 사용자 조회 (사용자명으로)
//     */
//    @GetMapping("/by-user-id/{userId}")
//    fun getUserByUserId(@PathVariable userId: String): ResponseEntity<UserResponse> {
//        val response = userUseCase.getUserByUserId(userId)
//        return ResponseEntity.ok(response)
//    }
//
//    /**
//     * 전체 사용자 목록 조회
//     */
//    @GetMapping
//    fun getAllUsers(
//        @RequestParam(defaultValue = "0") page: Int,
//        @RequestParam(defaultValue = "20") size: Int,
//        @RequestParam(required = false) userType: String?,
//        @RequestParam(required = false) status: String?
//    ): ResponseEntity<List<UserResponse>> {
//        val responses = userUseCase.getAllUsers(page, size, userType, status)
//        return ResponseEntity.ok(responses)
//    }
//
//    /**
//     * 사용자 정보 수정
//     */
//    @PutMapping("/{id}")
//    fun updateUser(
//        @PathVariable id: Long,
//        @Valid @RequestBody request: UserUpdateRequest
//    ): ResponseEntity<UserResponse> {
//        val response = userUseCase.updateUser(id, request)
//        return ResponseEntity.ok(response)
//    }
//
//    /**
//     * 사용자 상태 변경 (활성화/비활성화/정지)
//     */
//    @PatchMapping("/{id}/status")
//    fun changeUserStatus(
//        @PathVariable id: Long,
//        @RequestParam status: String
//    ): ResponseEntity<UserResponse> {
//        val response = userUseCase.changeUserStatus(id, status)
//        return ResponseEntity.ok(response)
//    }
//
//    /**
//     * 사용자 삭제 (논리적 삭제)
//     */
//    @DeleteMapping("/{id}")
//    fun deleteUser(@PathVariable id: Long): ResponseEntity<Void> {
//        userUseCase.deleteUser(id)
//        return ResponseEntity.noContent().build()
//    }
//
//    /**
//     * 비밀번호 변경
//     */
//    @PatchMapping("/{id}/password")
//    fun changePassword(
//        @PathVariable id: Long,
//        @RequestParam oldPassword: String,
//        @RequestParam newPassword: String
//    ): ResponseEntity<Void> {
//        userUseCase.changePassword(id, oldPassword, newPassword)
//        return ResponseEntity.noContent().build()
//    }
//
//    /**
//     * 사용자 ID 중복 확인
//     */
//    @GetMapping("/check-duplicate/{userId}")
//    fun checkUserIdDuplicate(@PathVariable userId: String): ResponseEntity<Boolean> {
//        val isDuplicate = userUseCase.checkUserIdDuplicate(userId)
//        return ResponseEntity.ok(isDuplicate)
//    }
}