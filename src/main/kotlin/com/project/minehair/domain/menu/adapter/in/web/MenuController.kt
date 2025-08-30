package com.project.minehair.domain.menu.adapter.`in`.web

import com.project.minehair.domain.menu.adapter.`in`.web.dto.ChangeMenuOrderNoRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.application.port.`in`.MenuUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "메뉴 API", description = "메뉴 API")
@RestController
@RequestMapping("/api/menus")
class MenuController(
    private val menuUseCase: MenuUseCase
) {

    @Operation(summary = "메뉴 순서 변경", description = "메뉴의 순서를 변경합니다.")
    @PatchMapping("/change/order-no")
    fun changeMenuOrderNo(@Valid @RequestBody request: List<ChangeMenuOrderNoRequest>): BaseResponse<List<MenuResponse>> {
        return BaseResponse.success(menuUseCase.changeMenuOrderNo(request))
    }

    @Operation(summary = "컨텐츠용 메뉴 조회", description = "컨텐츠용 메뉴 조회")
    @GetMapping("/contents")
    fun getContentsMenuList(): BaseResponse<List<MenuResponse>> {
        return BaseResponse.success(menuUseCase.getContentsMenuList())
    }

}