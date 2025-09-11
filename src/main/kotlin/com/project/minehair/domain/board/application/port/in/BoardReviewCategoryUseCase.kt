package com.project.minehair.domain.board.application.port.`in`

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewCategoryResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewCategoryRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewCategoryRequest

interface BoardReviewCategoryUseCase {

    /**
     * Review 카테고리 조회 (페이지)
     */
    fun getBoardReviewCategoryList(): List<BoardReviewCategoryResponse>

    /**
     * Review 카테고리 상세조회
     */
    fun getBoardReviewCategoryDetails(id: Long): BoardReviewCategoryResponse

    /**
     * Review 카테고리 생성
     */
    fun createBoardReviewCategory(request: CreateBoardReviewCategoryRequest): BoardReviewCategoryResponse

    /**
     * Review 카테고리 수정
     */
    fun updateBoardReviewCategory(id: Long, request: UpdateBoardReviewCategoryRequest): BoardReviewCategoryResponse

    /**
     * Review 카테고리 삭제
     */
    fun deleteBoardReviewCategory(id: Long): BoardReviewCategoryResponse

}