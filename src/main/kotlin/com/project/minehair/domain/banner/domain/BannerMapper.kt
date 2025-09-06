package com.project.minehair.domain.banner.domain

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.CreateBannerRequest
import com.project.minehair.domain.banner.adapter.out.persistence.BannerJpaEntity
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class BannerMapper {

    fun toDomain(entity: BannerJpaEntity): Banner {
        return Banner(
            id = entity.id,
            bannerType = entity.bannerType,
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

    fun toDomain(entity: CreateBannerRequest): Banner {
        return Banner(
            bannerType = entity.bannerType,
            content = entity.content,
            color = entity.color,
            textColor = entity.textColor,
            link = entity.link,
            imageUrl = entity.imageUrl,
            isPost = true,
            status = Status.active,
            createdAt = LocalDateTime.now(),
        )
    }

    fun toEntity(banner: Banner): BannerJpaEntity {
        return BannerJpaEntity(
            id = banner.id,
            bannerType = banner.bannerType,
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
            id = banner.id,
            bannerType = banner.bannerType,
            content = banner.content,
            color = banner.color,
            textColor = banner.textColor,
            link = banner.link,
            imageUrl = banner.imageUrl,
            isPost = banner.isPost,
        )
    }

    fun toResponseList(banners: List<Banner>): List<BannerResponse> {
        return banners.map { toResponse(it) }
    }

}