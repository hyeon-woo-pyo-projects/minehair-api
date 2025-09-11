package com.project.minehair.domain.board.application.service

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewCategoryResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewCategoryRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewCategoryRequest
import com.project.minehair.domain.board.application.port.`in`.BoardReviewCategoryUseCase
import com.project.minehair.domain.board.application.port.out.BoardReviewCategoryPersistencePort
import com.project.minehair.domain.board.domain.BoardReviewCategoryMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BoardReviewCategoryService(
    private val boardReviewCategoryPersistencePort: BoardReviewCategoryPersistencePort,
    private val boardReviewCategoryMapper: BoardReviewCategoryMapper
) : BoardReviewCategoryUseCase {

    override fun getBoardReviewCategoryList(): List<BoardReviewCategoryResponse> {
        return boardReviewCategoryPersistencePort.findAllActiveState()
            .let { boardReviewCategoryMapper.toResponseList(it) }
    }

    override fun getBoardReviewCategoryDetails(id: Long): BoardReviewCategoryResponse {
        val boardReviewCategory = boardReviewCategoryPersistencePort.findByIdActiveState(id)
        return boardReviewCategoryMapper.toResponse(boardReviewCategory)
    }

    @Transactional
    override fun createBoardReviewCategory(request: CreateBoardReviewCategoryRequest): BoardReviewCategoryResponse {
        val boardReviewCategoryForCreate = boardReviewCategoryMapper.toDomain(request)
        val updatedDomain = boardReviewCategoryForCreate.updateOrderNo(this.getNextOrderNo())
        val createdBoardReviewCategory = boardReviewCategoryPersistencePort.save(updatedDomain)
        return boardReviewCategoryMapper.toResponse(createdBoardReviewCategory)
    }

    @Transactional
    override fun updateBoardReviewCategory(
        id: Long,
        request: UpdateBoardReviewCategoryRequest
    ): BoardReviewCategoryResponse {
        val boardReviewCategory = boardReviewCategoryPersistencePort.findByIdActiveState(id)
        val boardReviewCategoryForUpdate = boardReviewCategory.update(request)
        val updatedBoardReviewCategory = boardReviewCategoryPersistencePort.save(boardReviewCategoryForUpdate)
        return boardReviewCategoryMapper.toResponse(updatedBoardReviewCategory)
    }

    @Transactional
    override fun deleteBoardReviewCategory(id: Long): BoardReviewCategoryResponse {
        val boardReviewCategory = boardReviewCategoryPersistencePort.findByIdActiveState(id)
        val boardReviewCategoryForDelete = boardReviewCategory.delete()
        val deletedBoardReviewCategory = boardReviewCategoryPersistencePort.save(boardReviewCategoryForDelete)
        return boardReviewCategoryMapper.toResponse(deletedBoardReviewCategory)
    }

    // ---------- [private method] -----------

    private fun getNextOrderNo(): Int {
        return boardReviewCategoryPersistencePort.getMaxOrderNo().plus(1)
    }


}