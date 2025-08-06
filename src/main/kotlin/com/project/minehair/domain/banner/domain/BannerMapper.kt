package com.project.minehair.domain.banner.domain

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.out.persistence.BannerJpaEntity
import org.springframework.stereotype.Component

@Component
class BannerMapper {

    fun toDomain(entity: BannerJpaEntity): Banner {
        return Banner(
            id = entity.id,
            content = entity.content,
            color = entity.color,
            textColor = entity.textColor,
            link = entity.link,
            imageUrl = entity.imageUrl,
            isPost = entity.isPost,
            status = entity.status,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }

    fun toEntity(banner: Banner): BannerJpaEntity {
        return BannerJpaEntity(
            id = banner.id,
            content = banner.content,
            color = banner.color,
            textColor = banner.textColor,
            link = banner.link,
            imageUrl = banner.imageUrl,
            isPost = banner.isPost,
            status = banner.status,
            createdAt = banner.createdAt,
            updatedAt = banner.updatedAt
        )
    }

    fun toResponse(banner: Banner): BannerResponse {
        return BannerResponse(
            content = banner.content,
            color = banner.color,
            textColor = banner.textColor,
            link = banner.link,
            imageUrl = banner.imageUrl,
            isPost = banner.isPost,
        )
    }

}