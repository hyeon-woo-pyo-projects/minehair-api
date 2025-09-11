package com.project.minehair.domain.board.application.port.out

import com.project.minehair.domain.board.domain.BoardReview
import org.springframework.data.domain.Page

interface BoardReviewPersistencePort {
    /**
     * QNA 게시판 조회 (페이지)
     */
    fun findAllPageActiveState(categoryId: Long?, page: Int, size: Int): Page<BoardReview>

    /**
     * QNA 게시판 상세조회
     */
    fun findByIdActiveState(id: Long): BoardReview

    /**
     * QNA 게시판 생성
     */
    fun save(boardReview: BoardReview): BoardReview

}