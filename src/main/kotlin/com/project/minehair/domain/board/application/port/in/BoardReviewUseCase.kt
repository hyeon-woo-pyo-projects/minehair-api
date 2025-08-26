package com.project.minehair.domain.board.application.port.`in`

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewPageRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewRequest
import org.springframework.data.domain.Page

interface BoardReviewUseCase {

    /**
     * Review 게시판 조회 (페이지)
     */
    fun getBoardReviewPage(request: BoardReviewPageRequest): Page<BoardReviewResponse>

    /**
     * Review 게시판 상세조회
     */
    fun getBoardReviewDetails(id: Long): BoardReviewResponse

    /**
     * Review 게시판 생성
     */
    fun createBoardReview(request: CreateBoardReviewRequest): BoardReviewResponse

    /**
     * Review 게시판 수정
     */
    fun updateBoardReview(id: Long, request: UpdateBoardReviewRequest): BoardReviewResponse

    /**
     * Review 게시판 삭제
     */
    fun deleteBoardReview(id: Long): BoardReviewResponse

}