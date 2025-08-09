package com.project.minehair.domain.role.adapter.`in`.web

import com.project.minehair.domain.role.adapter.`in`.web.dto.CreateRoleMenuRequest
import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleMenuResponse
import com.project.minehair.domain.role.application.port.`in`.RoleMenuUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "역할 별 메뉴 API", description = "역할 별 메뉴 API")
@RestController
@RequestMapping("/api/role-menus")
class RoleMenuInboundWebAdapter(
    private val roleMenuUseCase: RoleMenuUseCase
) {

    @Operation(summary = "역할별 메뉴 목록 조회", description = "특정 역할에 할당된 메뉴 목록을 조회합니다")
    @GetMapping
    fun getMenusByRole(): BaseResponse<List<RoleMenuResponse>> {
        val menus = roleMenuUseCase.getMenusByRole()
        return BaseResponse.success(menus)
    }

    @Operation(summary = "메뉴 생성 및 역할 세팅", description = "메뉴 생성 및 역할 세팅")
    @PostMapping
    fun createMenuAndAssignRoles(@RequestBody createRoleMenuRequest: CreateRoleMenuRequest): BaseResponse<Nothing?> {
        roleMenuUseCase.createMenuAndAssignRoles(createRoleMenuRequest)
        return BaseResponse.ok()
    }

//    @Operation(summary = "역할-메뉴 관계 상세 조회", description = "특정 역할의 특정 메뉴 할당 정보를 조회합니다")
//    @GetMapping("/roles/{roleId}/menus/{menuId}")
//    fun getRoleMenuDetail(
//        @PathVariable roleId: Long,
//        @PathVariable menuId: Long
//    ): BaseResponse<RoleMenuResponse> {
//        val roleMenu = roleMenuUseCase.getRoleMenuDetail(roleId, menuId)
//        return BaseResponse.success(roleMenu)
//    }


//    @Operation(summary = "역할에 메뉴 할당", description = "특정 역할에 여러 메뉴를 할당합니다")
//    @PostMapping("/roles/{roleId}/menus")
//    fun assignMenusToRole(
//        @PathVariable roleId: Long,
//        @RequestBody request: AssignMenusRequest
//    ): ResponseEntity<Void> {
//        roleMenuUseCase.assignMenusToRole(roleId, request.menuIds)
//        return ResponseEntity.ok().build()
//    }
//
//    @Operation(summary = "역할의 메뉴 전체 교체", description = "특정 역할의 메뉴를 새로운 메뉴 목록으로 전체 교체합니다")
//    @PutMapping("/roles/{roleId}/menus")
//    fun replaceRoleMenus(
//        @PathVariable roleId: Long,
//        @RequestBody request: ReplaceMenusRequest
//    ): ResponseEntity<Void> {
//        roleMenuUseCase.replaceRoleMenus(roleId, request.menuIds)
//        return ResponseEntity.ok().build()
//    }
//
//    @Operation(summary = "역할에서 메뉴 제거", description = "특정 역할에서 특정 메뉴를 제거합니다")
//    @DeleteMapping("/roles/{roleId}/menus/{menuId}")
//    fun removeMenuFromRole(
//        @PathVariable roleId: Long,
//        @PathVariable menuId: Long
//    ): ResponseEntity<Void> {
//        roleMenuUseCase.removeMenuFromRole(roleId, menuId)
//        return ResponseEntity.noContent().build()
//    }

}