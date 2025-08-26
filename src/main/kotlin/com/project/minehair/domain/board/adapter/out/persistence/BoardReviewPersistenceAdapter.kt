package com.project.minehair.domain.board.adapter.out.persistence

import com.project.minehair.domain.board.application.port.out.BoardReviewPersistencePort
import com.project.minehair.domain.board.domain.BoardReview
import com.project.minehair.domain.board.domain.BoardReviewMapper
import com.project.minehair.global.enums.Status
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Component

@Component
class BoardReviewPersistenceAdapter(
    private val boardReviewJpaRepository: BoardReviewJpaRepository,
    private val boardReviewMapper: BoardReviewMapper
) : BoardReviewPersistencePort {

    override fun findAllPageActiveState(page: Int, size: Int): Page<BoardReview> {
        val pageable = PageRequest.of(page - 1, size) // page는 1부터 시작하므로 -1
        return boardReviewJpaRepository.findAllByStatusOrderByCreatedAtDesc(Status.active, pageable)
            .let { boardReviewMapper.toDomainPage(it) }
    }

    override fun findByIdActiveState(id: Long): BoardReview {
        return boardReviewJpaRepository.findByIdAndStatus(id, Status.active)
            .let { boardReviewMapper.toDomain(it) }
    }

    override fun save(boardReview: BoardReview): BoardReview {
        val entityForCreate = boardReviewMapper.toEntity(boardReview)
        val createdEntity = boardReviewJpaRepository.save(entityForCreate)
        return boardReviewMapper.toDomain(createdEntity)
    }

}