package com.project.minehair.domain.board.adapter.`in`.web

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewCategoryResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewCategoryRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewCategoryRequest
import com.project.minehair.domain.board.application.port.`in`.BoardReviewCategoryUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Review 게시판 카테고리 API", description = "Review 게시판 카테고리 API")
@RestController
@RequestMapping("/api/board/review/category")
class BoardReviewCategoryInboundWebAdapter(
    private val boardReviewCategoryUseCase: BoardReviewCategoryUseCase
) {

    @Operation(summary = "Review 카테고리 리스트 조회", description = "Review 카테고리 리스트 조회")
    @GetMapping
    fun getBoardReviewCategoryList(): BaseResponse<List<BoardReviewCategoryResponse>> {
        return BaseResponse.success(boardReviewCategoryUseCase.getBoardReviewCategoryList())
    }

    @Operation(summary = "Review 카테고리 상세조회", description = "Review 카테고리 상세조회")
    @GetMapping("/details/{id}")
    fun getBoardReviewCategoryDetails(@PathVariable id: Long): BaseResponse<BoardReviewCategoryResponse> {
        return BaseResponse.success(boardReviewCategoryUseCase.getBoardReviewCategoryDetails(id))
    }

    @Operation(summary = "Review 카테고리 작성", description = "Review 카테고리 작성")
    @PostMapping
    fun createBoardReviewCategory(@Valid @RequestBody request: CreateBoardReviewCategoryRequest): BaseResponse<BoardReviewCategoryResponse> {
        return BaseResponse.success(boardReviewCategoryUseCase.createBoardReviewCategory(request))
    }

    @Operation(summary = "Review 카테고리 수정", description = "Review 카테고리 수정")
    @PatchMapping("/{id}")
    fun updateBoardReviewCategory(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateBoardReviewCategoryRequest
    ): BaseResponse<BoardReviewCategoryResponse> {
        return BaseResponse.success(boardReviewCategoryUseCase.updateBoardReviewCategory(id, request))
    }

    @Operation(summary = "Review 카테고리 삭제", description = "Review 카테고리 삭제")
    @DeleteMapping("/{id}")
    fun deleteBoardReviewCategory(@PathVariable id: Long): BaseResponse<BoardReviewCategoryResponse> {
        return BaseResponse.success(boardReviewCategoryUseCase.deleteBoardReviewCategory(id))
    }


}