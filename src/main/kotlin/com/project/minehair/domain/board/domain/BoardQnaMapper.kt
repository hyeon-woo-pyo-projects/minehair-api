package com.project.minehair.domain.board.domain

import com.project.minehair.domain.board.adapter.`in`.web.dto.BoardQnaResponse
import com.project.minehair.domain.board.adapter.`in`.web.dto.CreateBoardQnaRequest
import com.project.minehair.domain.board.adapter.out.persistence.BoardQnaJpaEntity
import org.springframework.data.domain.Page
import org.springframework.stereotype.Component

@Component
class BoardQnaMapper {

    fun toDomain(entity: BoardQnaJpaEntity): BoardQna {
        return BoardQna(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            author = entity.author,
            viewCount = entity.viewCount
        )
    }

    fun toDomain(request: CreateBoardQnaRequest): BoardQna {
        return BoardQna(
            title = request.title,
            content = request.content,
        )
    }

    fun toDomainPage(entityPage: Page<BoardQnaJpaEntity>): Page<BoardQna> {
        return entityPage.map { toDomain(it) }
    }

    fun toResponse(boardQna: BoardQna): BoardQnaResponse {
        return BoardQnaResponse(
            id = boardQna.id!!,
            title = boardQna.title,
            content = boardQna.content,
            author = boardQna.author,
            viewCount = boardQna.viewCount
        )
    }

    fun toResponsePage(domainPage: Page<BoardQna>): Page<BoardQnaResponse> {
        return domainPage.map { toResponse(it) }
    }

    fun toEntity(domain: BoardQna): BoardQnaJpaEntity {
        return BoardQnaJpaEntity(
            id = domain.id,
            title = domain.title,
            content = domain.content,
            author = domain.author,
            viewCount = domain.viewCount,
            status = domain.status
        )
    }

}