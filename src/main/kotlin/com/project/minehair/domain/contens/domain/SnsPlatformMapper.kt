package com.project.minehair.domain.contens.domain

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateSnsPlatformRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.SnsPlatformResponse
import com.project.minehair.domain.contens.adapter.out.persistence.SnsPlatformJpaEntity
import org.springframework.stereotype.Component

@Component
class SnsPlatformMapper {

    fun toDomain(entity: SnsPlatformJpaEntity): SnsPlatform {
        return SnsPlatform(
            id = entity.id,
            orderNo = entity.orderNo,
            imageUrl = entity.imageUrl,
            linkUrl = entity.linkUrl,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomainList(entityList: List<SnsPlatformJpaEntity>): List<SnsPlatform> {
        return entityList.map { toDomain(it) }
    }

    fun toEntity(domain: SnsPlatform): SnsPlatformJpaEntity {
        return SnsPlatformJpaEntity(
            id = domain.id,
            orderNo = domain.orderNo,
            imageUrl = domain.imageUrl,
            linkUrl = domain.linkUrl,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    fun toResponse(domain: SnsPlatform): SnsPlatformResponse {
        return SnsPlatformResponse(
            id = domain.id!!,
            orderNo = domain.orderNo,
            imageUrl = domain.imageUrl,
            linkUrl = domain.linkUrl,
        )
    }

    fun toResponseList(domainList: List<SnsPlatform>): List<SnsPlatformResponse> {
        return domainList.map { toResponse(it) }
    }

    // create request -> domain
    fun toDomain(request: CreateSnsPlatformRequest): SnsPlatform {
        return SnsPlatform(
            id = null,
            orderNo = 0,
            imageUrl = request.imageUrl,
            linkUrl = request.linkUrl,
        )
    }

}