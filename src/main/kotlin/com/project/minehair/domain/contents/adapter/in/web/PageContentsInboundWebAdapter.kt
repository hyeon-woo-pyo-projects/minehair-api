package com.project.minehair.domain.contents.adapter.`in`.web

import com.project.minehair.domain.contents.adapter.`in`.web.dto.CreatePageContentsRequest
import com.project.minehair.domain.contents.adapter.`in`.web.dto.PageContentsResponse
import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdatePageContentsRequest
import com.project.minehair.domain.contents.application.port.`in`.PageContentsUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "페이지 컨텐츠 API", description = "페이지 컨텐츠 API")
@RestController
@RequestMapping("/api/page/contents")
class PageContentsInboundWebAdapter(
    private val pageContentsUseCase: PageContentsUseCase
) {

    @Operation(summary = "리스트 조회", description = "리스트 조회")
    @GetMapping("/{menuId}")
    fun getPageContentsList(@PathVariable menuId: Long): BaseResponse<List<PageContentsResponse>> {
        return BaseResponse.success(pageContentsUseCase.getPageContentsList(menuId))
    }

    @Operation(summary = "상세 조회", description = "상세 조회")
    @GetMapping("/details/{id}")
    fun getPageContentsDetails(@PathVariable id: Long): BaseResponse<PageContentsResponse> {
        return BaseResponse.success(pageContentsUseCase.getPageContentsDetails(id))
    }

    @Operation(summary = "생성", description = "생성")
    @PostMapping
    fun createPageContents(@Valid @RequestBody request: CreatePageContentsRequest): BaseResponse<PageContentsResponse> {
        return BaseResponse.success(pageContentsUseCase.createPageContents(request))
    }

    @Operation(summary = "수정", description = "수정")
    @PatchMapping("/{id}")
    fun updatePageContents(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdatePageContentsRequest
    ): BaseResponse<PageContentsResponse> {
        return BaseResponse.success(pageContentsUseCase.updatePageContents(id, request))
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/{id}")
    fun deletePageContents(@PathVariable id: Long): BaseResponse<PageContentsResponse> {
        return BaseResponse.success(pageContentsUseCase.deletePageContents(id))
    }

}