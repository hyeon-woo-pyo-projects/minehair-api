package com.project.minehair.domain.user.adapter.`in`.web

import com.project.minehair.domain.user.adapter.`in`.web.dto.*
import com.project.minehair.domain.user.application.port.`in`.UserUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "회원 API", description = "회원 API")
@RestController
@RequestMapping("/api/user")
class UserInboundWebAdapter(
    private val userUseCase: UserUseCase
) {

    @Operation(summary = "회원 가입", description = "회원 가입")
    @PostMapping
    fun createUser(@Valid @RequestBody request: CreateUserRequest): BaseResponse<UserResponse> {
        return BaseResponse.success(userUseCase.createUser(request))
    }

    @Operation(summary = "마이페이지 상세 정보 조회", description = "로그인 이후 마이페이지에서 상세 정보를 조회합니다.")
    @GetMapping("/my-page/details")
    fun getUserDetailsMyPage(): BaseResponse<UserResponse> {
        return BaseResponse.success(userUseCase.getUserDetailsMyPage())
    }

//    @Operation(summary = "상세 정보 조회", description = "상세 정보를 조회합니다.")
//    @GetMapping("/details/{id}")
//    fun getUserDetails(@PathVariable id: Long): BaseResponse<UserResponse> {
//        return BaseResponse.success(userUseCase.getUserDetails(id))
//    }

    @Operation(summary = "아이디 조회", description = "아이디 분실 시 이름과 전화번호로 아이디를 조회합니다.")
    @GetMapping("/user-id")
    fun getUserId(@Valid request: UserIdRequest): BaseResponse<String?> {
        return BaseResponse.success(userUseCase.getUserId(request))
    }

    @Operation(summary = "상세 정보 조회", description = "비밀번호 찾기 전 아이디, 이름, 번화번호로 상세 정보를 조회합니다.")
    @GetMapping("/details")
    fun getUserDetails(@Valid request: UserDetailsRequest): BaseResponse<UserResponse> {
        return BaseResponse.success(userUseCase.getUserDetails(request))
    }

    @Operation(summary = "비밀번호 변경", description = "휴대전화번호 인증 후 비밀번호를 변경합니다.")
    @PatchMapping("/password/{id}")
    fun updatePassword(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdatePasswordRequest
    ): BaseResponse<UserResponse> {
        return BaseResponse.success(userUseCase.updatePassword(id, request))
    }

    @Operation(summary = "회원 정보 수정", description = "회원 정보를 수정합니다.")
    @PatchMapping
    fun updateUser(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateUserRequest
    ): BaseResponse<UserResponse> {
        return BaseResponse.success(userUseCase.updateUser(id, request))
    }

    @Operation(summary = "회원 탈퇴", description = "회원 탈퇴를 진행합니다.")
    @DeleteMapping()
    fun deleteUser(): BaseResponse<UserResponse> {
        return BaseResponse.success(userUseCase.deleteUser())
    }

}