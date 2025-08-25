package com.project.minehair.domain.board.application.service

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaPageRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardQnaRequest
import com.project.minehair.domain.board.application.port.`in`.BoardQnaUseCase
import com.project.minehair.domain.board.application.port.out.BoardQnaPersistencePort
import com.project.minehair.domain.board.domain.BoardQnaMapper
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BoardQnaService(
    private val boardQnaPersistencePort: BoardQnaPersistencePort,
    private val boardQnaMapper: BoardQnaMapper
): BoardQnaUseCase {

    override fun getBoardQnaPage(request: BoardQnaPageRequest): Page<BoardQnaResponse> {
        return boardQnaPersistencePort.findAllPageActiveState(request.page, request.size)
            .let { boardQnaMapper.toResponsePage(it) }
    }

    @Transactional
    override fun getBoardQnaDetails(id: Long): BoardQnaResponse {
        val boardQna = boardQnaPersistencePort.findByIdActiveState(id)
        val increasedViewCountBoardQna =  boardQna.incrementViewCount()
        val updatedBoardQna = boardQnaPersistencePort.save(increasedViewCountBoardQna)
        return boardQnaMapper.toResponse(updatedBoardQna)
    }

    @Transactional
    override fun createBoardQnaPage(request: CreateBoardQnaRequest): BoardQnaResponse {
        val requestWithAuthor = request.withAuthor("")
        val boardQnaForCreate = boardQnaMapper.toDomain(requestWithAuthor)
        val createdBoardQna = boardQnaPersistencePort.save(boardQnaForCreate)
        return boardQnaMapper.toResponse(createdBoardQna)
    }



}