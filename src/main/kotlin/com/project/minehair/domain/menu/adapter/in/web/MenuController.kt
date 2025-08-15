package com.project.minehair.domain.menu.adapter.`in`.web

import com.project.minehair.domain.menu.adapter.`in`.web.dto.ChangeMenuOrderNoRequest
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.application.port.`in`.MenuUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// MenuController.kt - REST 컨트롤러
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

//    @PostMapping
//    fun createMenu(@RequestBody request: CreateMenuRequest): MenuResponse {
//        return menuUseCase.createMenu(request)
//    }
//
//    @GetMapping("/{id}")
//    fun getMenu(@PathVariable id: Long): MenuResponse {
//        return menuUseCase.getMenuById(id)
//    }
//
//    @GetMapping
//    fun getAllMenus(): List<MenuResponse> {
//        return menuUseCase.getAllMenus()
//    }
//
//    @PutMapping("/{id}")
//    fun updateMenu(@PathVariable id: Long, @RequestBody request: UpdateMenuRequest): MenuResponse {
//        return menuUseCase.updateMenu(id, request)
//    }
//
//    @DeleteMapping("/{id}")
//    fun deleteMenu(@PathVariable id: Long) {
//        menuUseCase.deleteMenu(id)
//    }


}