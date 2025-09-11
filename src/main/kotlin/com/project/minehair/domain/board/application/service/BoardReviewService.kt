package com.project.minehair.domain.board.application.service

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewPageRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewRequest
import com.project.minehair.domain.board.application.port.`in`.BoardReviewUseCase
import com.project.minehair.domain.board.application.port.out.BoardReviewPersistencePort
import com.project.minehair.domain.board.domain.BoardReviewMapper
import org.springframework.data.domain.Page
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BoardReviewService(
    private val boardReviewPersistencePort: BoardReviewPersistencePort,
    private val boardReviewMapper: BoardReviewMapper
) : BoardReviewUseCase {

    override fun getBoardReviewPage(categoryId: Long?, request: BoardReviewPageRequest): Page<BoardReviewResponse> {
        return boardReviewPersistencePort.findAllPageActiveState(categoryId, request.page, request.size)
            .let { boardReviewMapper.toResponsePage(it) }
    }

    @Transactional
    override fun getBoardReviewDetails(id: Long): BoardReviewResponse {
        val boardReview = boardReviewPersistencePort.findByIdActiveState(id)
        val updatedBoardReview = boardReviewPersistencePort.save(boardReview)
        return boardReviewMapper.toResponse(updatedBoardReview)
    }

    @Transactional
    override fun createBoardReview(request: CreateBoardReviewRequest): BoardReviewResponse {
        val boardReviewForCreate = boardReviewMapper.toDomain(request)
        val createdBoardReview = boardReviewPersistencePort.save(boardReviewForCreate)
        return boardReviewMapper.toResponse(createdBoardReview)
    }

    @Transactional
    override fun updateBoardReview(id: Long, request: UpdateBoardReviewRequest): BoardReviewResponse {
        val boardReview = boardReviewPersistencePort.findByIdActiveState(id)
        val boardReviewForUpdate = boardReview.update(request)
        val updatedBoardReview = boardReviewPersistencePort.save(boardReviewForUpdate)
        return boardReviewMapper.toResponse(updatedBoardReview)
    }

    @Transactional
    override fun deleteBoardReview(id: Long): BoardReviewResponse {
        val boardReview = boardReviewPersistencePort.findByIdActiveState(id)
        val boardReviewForDelete = boardReview.delete()
        val deletedBoardReview = boardReviewPersistencePort.save(boardReviewForDelete)
        return boardReviewMapper.toResponse(deletedBoardReview)
    }


}