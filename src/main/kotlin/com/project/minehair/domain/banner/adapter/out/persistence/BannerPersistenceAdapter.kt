package com.project.minehair.domain.banner.adapter.out.persistence

import com.project.minehair.domain.banner.application.port.out.BannerPersistencePort
import com.project.minehair.domain.banner.domain.Banner
import com.project.minehair.domain.banner.domain.BannerMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.enums.Status
import com.project.minehair.global.exception.BusinessException
import org.springframework.stereotype.Component

@Component
class BannerPersistenceAdapter(
    private val bannerJpaRepository: BannerJpaRepository,
    private val bannerMapper: BannerMapper
) : BannerPersistencePort {

    override fun findAllActiveStatus(): List<Banner> {
        return bannerJpaRepository.findAllByStatus(Status.active)
            .map { bannerMapper.toDomain(it) }
    }

    override fun findById(id: Long): Banner {
        val banner = bannerJpaRepository.findByIdAndStatus(id, Status.active)
            ?: throw BusinessException(ErrorCode.NOT_FOUND)
        return bannerMapper.toDomain(banner)
    }

    override fun save(banner: Banner): Banner {
        val bannerEntity = bannerMapper.toEntity(banner)
        return bannerMapper.toDomain(bannerJpaRepository.save(bannerEntity))
    }

}