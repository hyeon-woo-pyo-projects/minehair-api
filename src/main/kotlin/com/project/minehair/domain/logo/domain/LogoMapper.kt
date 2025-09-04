package com.project.minehair.domain.logo.domain

import com.project.minehair.domain.logo.adapter.`in`.web.dto.CreateLogoRequest
import com.project.minehair.domain.logo.adapter.`in`.web.dto.LogoResponse
import com.project.minehair.domain.logo.adapter.out.persistence.LogoJpaEntity
import org.springframework.stereotype.Component

@Component
class LogoMapper {

    fun toDomain(entity: LogoJpaEntity): Logo {
        return Logo(
            id = entity.id,
            logoType = entity.logoType,
            description = entity.description,
            imageUrl = entity.imageUrl,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomainList(entityList: List<LogoJpaEntity>): List<Logo> {
        return entityList.map { toDomain(it) }
    }

    fun toEntity(domain: Logo): LogoJpaEntity {
        return LogoJpaEntity(
            id = domain.id,
            logoType = domain.logoType,
            description = domain.description,
            imageUrl = domain.imageUrl,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    fun toResponse(domain: Logo): LogoResponse {
        return LogoResponse(
            id = domain.id!!,
            logoType = domain.logoType,
            description = domain.description,
            imageUrl = domain.imageUrl,
        )
    }

    fun toResponseList(domainList: List<Logo>): List<LogoResponse> {
        return domainList.map { toResponse(it) }
    }

    // create request -> domain
    fun toDomain(request: CreateLogoRequest): Logo {
        return Logo(
            id = null,
            logoType = request.logoType,
            description = request.description,
            imageUrl = request.imageUrl,
        )
    }

}