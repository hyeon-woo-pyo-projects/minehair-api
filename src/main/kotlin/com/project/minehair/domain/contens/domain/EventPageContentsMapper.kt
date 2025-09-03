package com.project.minehair.domain.contens.domain

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateEventPageContentsRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.EventPageContentsResponse
import com.project.minehair.domain.contens.adapter.out.persistence.EventPageContentsJpaEntity
import org.springframework.stereotype.Component

@Component
class EventPageContentsMapper {

    fun toDomain(entity: EventPageContentsJpaEntity): EventPageContents {
        return EventPageContents(
            id = entity.id,
            orderNo = entity.orderNo,
            slideOrderNo = entity.slideOrderNo,
            imageUrl = entity.imageUrl,
            linkUrl = entity.linkUrl,
            status = entity.status,
            textContent = entity.textContent,
            isAddPost = entity.isAddPost,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomainList(entityList: List<EventPageContentsJpaEntity>): List<EventPageContents> {
        return entityList.map { toDomain(it) }
    }

    fun toEntity(domain: EventPageContents): EventPageContentsJpaEntity {
        return EventPageContentsJpaEntity(
            id = domain.id,
            orderNo = domain.orderNo,
            slideOrderNo = domain.slideOrderNo,
            imageUrl = domain.imageUrl,
            linkUrl = domain.linkUrl,
            textContent = domain.textContent,
            isAddPost = domain.isAddPost,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    fun toResponse(domain: EventPageContents): EventPageContentsResponse {
        return EventPageContentsResponse(
            id = domain.id!!,
            orderNo = domain.orderNo,
            slideOrderNo = domain.slideOrderNo,
            imageUrl = domain.imageUrl,
            linkUrl = domain.linkUrl,
            textContent = domain.textContent,
            isAddPost = domain.isAddPost,
        )
    }

    fun toResponseList(domainList: List<EventPageContents>): List<EventPageContentsResponse> {
        return domainList.map { toResponse(it) }
    }

    // create request -> domain
    fun toDomain(request: CreateEventPageContentsRequest): EventPageContents {
        return EventPageContents(
            id = null,
            orderNo = 0,
            slideOrderNo = 0,
            imageUrl = request.imageUrl,
            linkUrl = request.linkUrl,
            textContent = request.textContent,
            isAddPost = request.isAddPost,
        )
    }

}