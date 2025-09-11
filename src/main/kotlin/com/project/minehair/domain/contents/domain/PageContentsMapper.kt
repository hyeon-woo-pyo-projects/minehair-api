package com.project.minehair.domain.contents.domain

import com.project.minehair.domain.contents.adapter.`in`.web.dto.CreatePageContentsRequest
import com.project.minehair.domain.contents.adapter.`in`.web.dto.PageContentsResponse
import com.project.minehair.domain.contents.adapter.out.persistence.PageContentsJpaEntity
import org.springframework.stereotype.Component

@Component
class PageContentsMapper {

    fun toDomain(entity: PageContentsJpaEntity): PageContents {
        return PageContents(
            id = entity.id,
            menuId = entity.menuId,
            pageUrl = entity.pageUrl,
            contentsType = entity.contentsType,
            contentsUrl = entity.contentsUrl,
            orderNo = entity.orderNo,
            videoBackGroundUrl = entity.videoBackGroundUrl,
            consultingBackGroundUrl = entity.consultingBackGroundUrl,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomainList(entityList: List<PageContentsJpaEntity>): List<PageContents> {
        return entityList.map { toDomain(it) }
    }

    fun toEntity(domain: PageContents): PageContentsJpaEntity {
        return PageContentsJpaEntity(
            id = domain.id,
            menuId = domain.menuId,
            pageUrl = domain.pageUrl,
            contentsType = domain.contentsType,
            contentsUrl = domain.contentsUrl,
            orderNo = domain.orderNo,
            videoBackGroundUrl = domain.videoBackGroundUrl,
            consultingBackGroundUrl = domain.consultingBackGroundUrl,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    fun toResponse(domain: PageContents): PageContentsResponse {
        return PageContentsResponse(
            id = domain.id!!,
            menuId = domain.menuId,
            pageUrl = domain.pageUrl,
            contentsType = domain.contentsType,
            contentsUrl = domain.contentsUrl,
            orderNo = domain.orderNo,
            videoBackGroundUrl = domain.videoBackGroundUrl,
//            consultingBackGroundUrl = domain.consultingBackGroundUrl,
        )
    }

    fun toResponseList(domainList: List<PageContents>): List<PageContentsResponse> {
        return domainList.map { toResponse(it) }
    }

    // create request -> domain
    fun toDomain(request: CreatePageContentsRequest): PageContents {
        return PageContents(
            id = null,
            menuId = request.menuId,
            pageUrl = request.pageUrl,
            contentsType = request.contentsType,
            contentsUrl = request.contentsUrl,
            orderNo = 0,
            videoBackGroundUrl = request.videoBackGroundUrl,
            consultingBackGroundUrl = "",
        )
    }

}