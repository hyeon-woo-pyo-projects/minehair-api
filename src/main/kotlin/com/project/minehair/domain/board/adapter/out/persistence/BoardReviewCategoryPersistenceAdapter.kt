package com.project.minehair.domain.board.adapter.out.persistence

import com.project.minehair.domain.board.application.port.out.BoardReviewCategoryPersistencePort
import com.project.minehair.domain.board.domain.BoardReviewCategory
import com.project.minehair.domain.board.domain.BoardReviewCategoryMapper
import com.project.minehair.global.enums.Status
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class BoardReviewCategoryPersistenceAdapter(
    private val boardReviewCategoryJpaRepository: BoardReviewCategoryJpaRepository,
    private val boardReviewCategoryMapper: BoardReviewCategoryMapper
) : BoardReviewCategoryPersistencePort {

    override fun findAllActiveState(): List<BoardReviewCategory> {
        return boardReviewCategoryJpaRepository.findByStatus(Status.active)
            .let { boardReviewCategoryMapper.toDomainList(it) }
    }

    override fun findByIdActiveState(id: Long): BoardReviewCategory {
        return boardReviewCategoryJpaRepository.findByIdAndStatus(id, Status.active)
            .let { boardReviewCategoryMapper.toDomain(it) }
    }

    override fun getMaxOrderNo(): Int {
        return boardReviewCategoryJpaRepository.findTopByOrderByOrderNoDesc()?.orderNo ?: 0
    }

    override fun save(boardReviewCategory: BoardReviewCategory): BoardReviewCategory {
        val entityForCreate = boardReviewCategoryMapper.toEntity(boardReviewCategory)
        val createdEntity = boardReviewCategoryJpaRepository.save(entityForCreate)
        return boardReviewCategoryMapper.toDomain(createdEntity)
    }

}