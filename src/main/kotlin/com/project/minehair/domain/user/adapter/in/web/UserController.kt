package com.example.users.adapter.`in`.web

import com.project.minehair.domain.user.adapter.`in`.web.dto.UserCreateRequest
import com.project.minehair.domain.user.application.port.`in`.UserUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "회원 API", description = "회원 API")
@RestController
@RequestMapping("/api/users")
class UserController(
    private val userUseCase: UserUseCase
) {

    /**
     * 사용자 생성 (회원가입)
     */
    @Operation(summary = "회원 가입", description = "회원 가입")
    @PostMapping
    fun createUser(@Valid @RequestBody request: UserCreateRequest): BaseResponse<Nothing?> {
        val response = userUseCase.createUser(request)
        return BaseResponse.ok()
    }

//    /**
//     * 사용자 조회 (ID로)
//     */
//    @GetMapping("/{id}")
//    fun getUserById(@PathVariable id: Long): BaseResponse<UserResponse> {
//        val response = userUseCase.getUserById(id)
//        return BaseResponse.success(response)
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
//    ): BaseResponse<List<UserResponse>> {
//        val responses = userUseCase.getAllUsers(page, size, userType, status)
//        return BaseResponse.success(responses)
//    }
//
//    /**
//     * 사용자 정보 수정
//     */
//    @PutMapping("/{id}")
//    fun updateUser(
//        @PathVariable id: Long,
//        @Valid @RequestBody request: UserUpdateRequest
//    ): BaseResponse<UserResponse> {
//        val response = userUseCase.updateUser(id, request)
//        return BaseResponse.success(response)
//    }
//
//    /**
//     * 사용자 상태 변경 (활성화/비활성화/정지)
//     */
//    @PatchMapping("/{id}/status")
//    fun changeUserStatus(
//        @PathVariable id: Long,
//        @RequestParam status: String
//    ): BaseResponse<UserResponse> {
//        val response = userUseCase.changeUserStatus(id, status)
//        return BaseResponse.success(response)
//    }
//
//    /**
//     * 사용자 삭제 (논리적 삭제)
//     */
//    @DeleteMapping("/{id}")
//    fun deleteUser(@PathVariable id: Long): BaseResponse<Nothing?> {
//        userUseCase.deleteUser(id)
//        return BaseResponse.ok()
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
//    ): BaseResponse<Nothing?> {
//        userUseCase.changePassword(id, oldPassword, newPassword)
//        return BaseResponse.ok()
//    }
//
//    /**
//     * 사용자 ID 중복 확인
//     */
//    @GetMapping("/check-duplicate/{userId}")
//    fun checkUserIdDuplicate(@PathVariable userId: String): BaseResponse<Boolean> {
//        val isDuplicate = userUseCase.checkUserIdDuplicate(userId)
//        return BaseResponse.success(isDuplicate)
//    }
}