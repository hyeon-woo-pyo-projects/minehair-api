package com.project.minehair.domain.board.application.port.out

import com.project.minehair.domain.board.domain.BoardQna
import org.springframework.data.domain.Page

interface BoardQnaPersistencePort {
    /**
     * QNA 게시판 조회 (페이지)
     */
    fun findAllPageActiveState(page: Int, size: Int): Page<BoardQna>

    /**
     * QNA 게시판 상세조회
     */
    fun findByIdActiveState(id: Long): BoardQna

    /**
     * QNA 게시판 생성
     */
    fun save(boardQna: BoardQna): BoardQna

}