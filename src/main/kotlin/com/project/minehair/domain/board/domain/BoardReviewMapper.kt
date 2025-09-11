package com.project.minehair.domain.board.domain

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewRequest
import com.project.minehair.domain.board.adapter.out.persistence.BoardReviewJpaEntity
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class BoardReviewMapper {

    fun toDomain(entity: BoardReviewJpaEntity): BoardReview {
        return BoardReview(
            id = entity.id,
            categoryId = entity.categoryId,
            title = entity.title,
            content = entity.content,
            imageUrl = entity.imageUrl,
        )
    }

    fun toDomain(request: CreateBoardReviewRequest): BoardReview {
        return BoardReview(
            id = null,
            categoryId = request.categoryId,
            title = request.title,
            content = request.content,
            imageUrl = request.imageUrl,
        )
    }

    fun toDomainPage(entityPage: Page<BoardReviewJpaEntity>): Page<BoardReview> {
        return entityPage.map { toDomain(it) }
    }

    fun toResponse(domain: BoardReview): BoardReviewResponse {
        return BoardReviewResponse(
            id = domain.id!!,
            categoryId = domain.categoryId,
            title = domain.title,
            content = domain.content,
            imageUrl = domain.imageUrl,
        )
    }

    fun toResponsePage(domainPage: Page<BoardReview>): Page<BoardReviewResponse> {
        return domainPage.map { toResponse(it) }
    }

    fun toEntity(domain: BoardReview): BoardReviewJpaEntity {
        return BoardReviewJpaEntity(
            id = domain.id,
            categoryId = domain.categoryId,
            title = domain.title,
            content = domain.content,
            imageUrl = domain.imageUrl,
            status = domain.status,
            updatedId = domain.updatedId,
        )
    }

}