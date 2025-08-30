package com.project.minehair.domain.contens.adapter.`in`.web

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateMenuContentsRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.MenuContentsResponse
import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateMenuContentsRequest
import com.project.minehair.domain.contens.application.port.`in`.MenuContentsUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "메뉴 컨텐츠 API", description = "메뉴 컨텐츠 API")
@RestController
@RequestMapping("/api/menu/contents")
class MenuContentsInboundWebAdapter(
    private val menuContentsUseCase: MenuContentsUseCase
) {

    @Operation(summary = "리스트 조회", description = "리스트 조회")
    @GetMapping("/{menuId}")
    fun getMenuContentsList(@PathVariable menuId: Long): BaseResponse<List<MenuContentsResponse>> {
        return BaseResponse.success(menuContentsUseCase.getMenuContentsList(menuId))
    }

    @Operation(summary = "상세 조회", description = "상세 조회")
    @GetMapping("/details/{id}")
    fun getMenuContentsDetails(@PathVariable id: Long): BaseResponse<MenuContentsResponse> {
        return BaseResponse.success(menuContentsUseCase.getMenuContentsDetails(id))
    }

    @Operation(summary = "생성", description = "생성")
    @PostMapping
    fun createMenuContents(@Valid @RequestBody request: CreateMenuContentsRequest): BaseResponse<MenuContentsResponse> {
        return BaseResponse.success(menuContentsUseCase.createMenuContents(request))
    }

    @Operation(summary = "수정", description = "수정")
    @PatchMapping("/{id}")
    fun updateMenuContents(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateMenuContentsRequest
    ): BaseResponse<MenuContentsResponse> {
        return BaseResponse.success(menuContentsUseCase.updateMenuContents(id, request))
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/{id}")
    fun deleteMenuContents(@PathVariable id: Long): BaseResponse<MenuContentsResponse> {
        return BaseResponse.success(menuContentsUseCase.deleteMenuContents(id))
    }

}