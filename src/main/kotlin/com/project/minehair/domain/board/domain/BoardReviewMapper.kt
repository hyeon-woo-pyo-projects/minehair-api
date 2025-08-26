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
            title = entity.title,
            content = entity.content,
            author = entity.author,
            viewCount = entity.viewCount
        )
    }

    fun toDomain(request: CreateBoardReviewRequest): BoardReview {
        return BoardReview(
            title = request.title,
            content = request.content,
        )
    }

    fun toDomainPage(entityPage: Page<BoardReviewJpaEntity>): Page<BoardReview> {
        return entityPage.map { toDomain(it) }
    }

    fun toResponse(domain: BoardReview): BoardReviewResponse {
        return BoardReviewResponse(
            id = domain.id!!,
            title = domain.title,
            content = domain.content,
            author = domain.author,
            viewCount = domain.viewCount
        )
    }

    fun toResponsePage(domainPage: Page<BoardReview>): Page<BoardReviewResponse> {
        return domainPage.map { toResponse(it) }
    }

    fun toEntity(domain: BoardReview): BoardReviewJpaEntity {
        return BoardReviewJpaEntity(
            id = domain.id,
            title = domain.title,
            content = domain.content,
            author = domain.author,
            viewCount = domain.viewCount,
            status = domain.status
        )
    }

}