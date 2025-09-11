package com.project.minehair.domain.board.domain

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardReviewCategoryResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardReviewCategoryRequest
import com.project.minehair.domain.board.adapter.out.persistence.BoardReviewCategoryJpaEntity
import org.springframework.stereotype.Component

@Component
class BoardReviewCategoryMapper {

    fun toDomain(entity: BoardReviewCategoryJpaEntity): BoardReviewCategory {
        return BoardReviewCategory(
            id = entity.id,
            name = entity.name,
            orderNo = entity.orderNo,
        )
    }

    fun toDomain(request: CreateBoardReviewCategoryRequest): BoardReviewCategory {
        return BoardReviewCategory(
            id = null,
            name = request.name,
            orderNo = 0,
        )
    }

    fun toDomainList(entityList: List<BoardReviewCategoryJpaEntity>): List<BoardReviewCategory> {
        return entityList.map { toDomain(it) }
    }

    fun toResponse(domain: BoardReviewCategory): BoardReviewCategoryResponse {
        return BoardReviewCategoryResponse(
            id = domain.id!!,
            name = domain.name,
            orderNo = domain.orderNo,
        )
    }

    fun toResponseList(domainList: List<BoardReviewCategory>): List<BoardReviewCategoryResponse> {
        return domainList.map { toResponse(it) }
    }

    fun toEntity(domain: BoardReviewCategory): BoardReviewCategoryJpaEntity {
        return BoardReviewCategoryJpaEntity(
            id = domain.id,
            name = domain.name,
            orderNo = domain.orderNo,
            status = domain.status,
            updatedAt = domain.updatedAt,
        )
    }
}