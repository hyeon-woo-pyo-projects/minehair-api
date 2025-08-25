package com.project.minehair.domain.board.adapter.`in`.web

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaPageRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardQnaRequest
import com.project.minehair.domain.board.application.port.`in`.BoardQnaUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "QNA 게시판 API", description = "QNA 게시판 API")
@RestController
@RequestMapping("/api/board/qna")
class BoardQnaInboundWebAdapter(
    private val boardQnaUseCase: BoardQnaUseCase
) {

    /**
     * QNA 게시판 조회 (페이지)
     */
    @Operation(summary = "QNA 게시판 조회 (페이지)", description = "QNA 게시판 조회 (페이지)")
    @GetMapping("/page")
    fun getBoardQnaPage(@Valid @ModelAttribute request: BoardQnaPageRequest): BaseResponse<List<BoardQnaResponse>> {

        val boardQnaPage = boardQnaUseCase.getBoardQnaPage(request)

        // Page 객체에서 PaginationInfo 추출
        val paginationInfo = BaseResponse.createPaginationInfo(
            currentPage = boardQnaPage.number + 1, // Page는 0부터 시작하므로 +1
            totalElements = boardQnaPage.totalElements,
            size = boardQnaPage.size
        )

        return BaseResponse.success(boardQnaPage.content, paginationInfo)
    }

    /**
     * QNA 게시판 상세조회
     */
    @Operation(summary = "QNA 게시판 상세조회", description = "QNA 게시판 상세조회")
    @GetMapping("/details/{id}")
    fun getBoardQnaDetails (@PathVariable id: Long): BaseResponse<BoardQnaResponse> {
        return BaseResponse.success(boardQnaUseCase.getBoardQnaDetails(id))
    }

    /**
     * QNA 게시판 생성
     */
    @Operation(summary = "QNA 게시판 조회 (페이지)", description = "QNA 게시판 조회 (페이지)")
    @PostMapping
    fun createBoardQnaPage(@Valid @RequestBody request: CreateBoardQnaRequest) : BaseResponse<BoardQnaResponse> {
        return BaseResponse.success(boardQnaUseCase.createBoardQnaPage(request))
    }


}