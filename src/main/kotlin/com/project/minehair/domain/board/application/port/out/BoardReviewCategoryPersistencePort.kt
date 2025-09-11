package com.project.minehair.domain.board.application.port.out

import com.project.minehair.domain.board.domain.BoardReviewCategory

interface BoardReviewCategoryPersistencePort {
    /**
     * review 카테고리 조회
     */
    fun findAllActiveState(): List<BoardReviewCategory>

    /**
     * review 카테고리 상세조회
     */
    fun findByIdActiveState(id: Long): BoardReviewCategory

    /**
     * max order_no 조회
     */
    fun getMaxOrderNo(): Int

    /**
     * review 카테고리 생성
     */
    fun save(boardReviewCategory: BoardReviewCategory): BoardReviewCategory

}