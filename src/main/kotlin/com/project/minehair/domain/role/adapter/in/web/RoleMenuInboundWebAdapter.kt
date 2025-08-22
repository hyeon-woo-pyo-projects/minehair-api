package com.project.minehair.domain.role.adapter.`in`.web

import com.project.minehair.domain.role.adapter.`in`.web.dto.CreateRoleMenuRequest
import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleMenuResponse
import com.project.minehair.domain.role.adapter.`in`.web.dto.UpdateRoleMenuRequest
import com.project.minehair.domain.role.application.port.`in`.RoleMenuUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

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

    @Operation(summary = "역할별 메뉴 목록 조회(관리자)", description = "관리자 메뉴에서 메뉴 목록을 조회합니다")
    @GetMapping("/admin")
    fun getMenusByRoleForAdmin(): BaseResponse<List<RoleMenuResponse>> {
        return BaseResponse.success(roleMenuUseCase.getMenusByRoleForAdmin())
    }

    @Operation(summary = "메뉴 생성 및 역할 세팅", description = "메뉴 생성 및 역할 세팅")
    @PostMapping
    fun createMenuAndAssignRoles(@RequestBody createRoleMenuRequest: CreateRoleMenuRequest): BaseResponse<Nothing?> {
        roleMenuUseCase.createMenuAndAssignRoles(createRoleMenuRequest)
        return BaseResponse.ok()
    }

    @Operation(summary = "메뉴, 역할 세팅 수정", description = "메뉴, 역할 세팅 수정")
    @PatchMapping("/{menuId}")
    fun updateMenuRole(
        @PathVariable menuId: Long,
        @RequestBody updateRoleMenuRequest: UpdateRoleMenuRequest
    ): BaseResponse<Nothing?> {
        roleMenuUseCase.updateMenuRole(menuId, updateRoleMenuRequest)
        return BaseResponse.ok()
    }

    @Operation(summary = "메뉴 삭제", description = "메뉴 삭제")
    @DeleteMapping("/{menuId}")
    fun deleteMenu(@PathVariable menuId: Long): BaseResponse<List<RoleMenuResponse>> {
        return BaseResponse.success(roleMenuUseCase.deleteMenuRole(menuId))
    }

}