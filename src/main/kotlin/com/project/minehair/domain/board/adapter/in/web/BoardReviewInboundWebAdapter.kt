package com.project.minehair.domain.board.adapter.`in`.web

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewPageRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewRequest
import com.project.minehair.domain.board.application.port.`in`.BoardReviewUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "Review 게시판 API", description = "Review 게시판 API")
@RestController
@RequestMapping("/api/board/review")
class BoardReviewInboundWebAdapter(
    private val boardReviewUseCase: BoardReviewUseCase
) {

    @Operation(summary = "Review 게시판 조회 (페이지)", description = "Review 게시판 조회 (페이지)")
    @GetMapping("/page")
    fun getBoardReviewPage(
        categoryId: Long?,
        @Valid @ModelAttribute request: BoardReviewPageRequest
    ): BaseResponse<List<BoardReviewResponse>> {
        val boardReviewPage = boardReviewUseCase.getBoardReviewPage(categoryId, request)
        // Page 객체에서 PaginationInfo 추출
        val paginationInfo = BaseResponse.createPaginationInfo(
            currentPage = boardReviewPage.number + 1, // Page는 0부터 시작하므로 +1
            totalElements = boardReviewPage.totalElements,
            size = boardReviewPage.size
        )
        return BaseResponse.success(boardReviewPage.content, paginationInfo)
    }

    @Operation(summary = "Review 게시판 상세조회", description = "Review 게시판 상세조회")
    @GetMapping("/details/{id}")
    fun getBoardReviewDetails(@PathVariable id: Long): BaseResponse<BoardReviewResponse> {
        return BaseResponse.success(boardReviewUseCase.getBoardReviewDetails(id))
    }

    @Operation(summary = "Review 게시판 작성", description = "Review 게시판 작성")
    @PostMapping
    fun createBoardReview(@Valid @RequestBody request: CreateBoardReviewRequest): BaseResponse<BoardReviewResponse> {
        return BaseResponse.success(boardReviewUseCase.createBoardReview(request))
    }

    @Operation(summary = "Review 게시판 수정", description = "Review 게시판 수정")
    @PatchMapping("/{id}")
    fun updateBoardReview(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateBoardReviewRequest
    ): BaseResponse<BoardReviewResponse> {
        return BaseResponse.success(boardReviewUseCase.updateBoardReview(id, request))
    }

    @Operation(summary = "Review 게시판 삭제", description = "Review 게시판 삭제")
    @DeleteMapping("/{id}")
    fun deleteBoardReview(@PathVariable id: Long): BaseResponse<BoardReviewResponse> {
        return BaseResponse.success(boardReviewUseCase.deleteBoardReview(id))
    }

}