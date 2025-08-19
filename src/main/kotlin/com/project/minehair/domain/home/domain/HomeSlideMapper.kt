package com.project.minehair.domain.home.domain

import com.project.minehair.domain.home.adapter.`in`.web.dto.CreateHomeSlideRequest
import com.project.minehair.domain.home.adapter.`in`.web.dto.HomeSlideResponse
import com.project.minehair.domain.home.adapter.out.persistence.HomeSlideJpaEntity
import org.springframework.stereotype.Component

@Component
class HomeSlideMapper {

    fun toDomain(entity: HomeSlideJpaEntity): HomeSlide {
        return HomeSlide(
            id = entity.id,
            imageUrl = entity.imageUrl,
            link = entity.link,
            orderNo = entity.orderNo,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomainList(entities: List<HomeSlideJpaEntity>): List<HomeSlide> {
        return entities.map { toDomain(it) }
    }

    fun toDomain(request: CreateHomeSlideRequest): HomeSlide {
        return HomeSlide(
            imageUrl = request.imageUrl,
            link = request.link,
            orderNo = request.orderNo
        )
    }

    fun toEntity(homeSlide: HomeSlide): HomeSlideJpaEntity {
        return HomeSlideJpaEntity(
            id = homeSlide.id,
            imageUrl = homeSlide.imageUrl,
            link = homeSlide.link,
            orderNo = homeSlide.orderNo,
            status = homeSlide.status,
            createdId = homeSlide.createdId,
            createdAt = homeSlide.createdAt,
            updatedId = homeSlide.updatedId,
            updatedAt = homeSlide.updatedAt
        )
    }

    fun toResponse(homeSlide: HomeSlide): HomeSlideResponse {
        return HomeSlideResponse(
            id = homeSlide.id!!,
            imageUrl = homeSlide.imageUrl,
            link = homeSlide.link,
            orderNo = homeSlide.orderNo
        )
    }

    fun toResponseList(homeSlides: List<HomeSlide>): List<HomeSlideResponse> {
        return homeSlides.map { toResponse(it) }
    }

}