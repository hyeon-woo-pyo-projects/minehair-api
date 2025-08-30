package com.project.minehair.domain.contens.domain

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateMenuContentsRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.MenuContentsResponse
import com.project.minehair.domain.contens.adapter.out.persistence.MenuContentsJpaEntity
import com.project.minehair.domain.menu.adapter.`in`.web.dto.MenuResponse
import com.project.minehair.domain.menu.domain.Menu
import org.springframework.stereotype.Component

@Component
class MenuContentsMapper {

    fun toDomain(entity: MenuContentsJpaEntity): MenuContents {
        return MenuContents(
            id = entity.id,
            menuId = entity.menuId,
            contentsType = entity.contentsType,
            contentsUrl = entity.contentsUrl,
            orderNo = entity.orderNo,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomainList(entityList: List<MenuContentsJpaEntity>): List<MenuContents> {
        return entityList.map { toDomain(it) }
    }

    fun toEntity(domain: MenuContents): MenuContentsJpaEntity {
        return MenuContentsJpaEntity(
            id = domain.id,
            menuId = domain.menuId,
            contentsType = domain.contentsType,
            contentsUrl = domain.contentsUrl,
            orderNo = domain.orderNo,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    fun toResponse(domain: MenuContents): MenuContentsResponse {
        return MenuContentsResponse(
            id = domain.id!!,
            menuId = domain.menuId,
            contentsType = domain.contentsType,
            contentsUrl = domain.contentsUrl,
            orderNo = domain.orderNo,
        )
    }

    fun toResponseList(domainList: List<MenuContents>): List<MenuContentsResponse> {
        return domainList.map { toResponse(it) }
    }

    // create request -> domain
    fun toDomain(request: CreateMenuContentsRequest): MenuContents {
        return MenuContents(
            id = null,
            menuId = request.menuId,
            contentsType = request.contentsType,
            contentsUrl = request.contentsUrl,
            orderNo = 0
        )
    }

}