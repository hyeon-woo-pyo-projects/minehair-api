package com.project.minehair.domain.board.application.port.`in`

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaPageRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardQnaRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardQnaRequest
import org.springframework.data.domain.Page

interface BoardQnaUseCase {

    /**
     * QNA 게시판 조회 (페이지)
     */
    fun getBoardQnaPage(request: BoardQnaPageRequest): Page<BoardQnaResponse>

    /**
     * QNA 게시판 상세조회
     */
    fun getBoardQnaDetails(id: Long): BoardQnaResponse

    /**
     * QNA 게시판 생성
     */
    fun createBoardQnaPage(request: CreateBoardQnaRequest): BoardQnaResponse

    /**
     * QNA 게시판 수정
     */
    fun updateBoardQnaPage(id: Long, request: UpdateBoardQnaRequest): BoardQnaResponse

    /**
     * QNA 게시판 삭제
     */
    fun deleteBoardQnaPage(id: Long): BoardQnaResponse

}