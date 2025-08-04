package com.project.minehair.domain.banner.adapter.out.persistence

import com.project.minehair.domain.banner.application.port.out.BannerPersistencePort
import com.project.minehair.domain.banner.domain.Banner
import com.project.minehair.domain.banner.domain.BannerMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class BannerPersistenceAdapter(
    private val bannerJpaRepository: BannerJpaRepository,
    private val bannerMapper: BannerMapper
) : BannerPersistencePort {

    override fun findByIsPost(isPost: Boolean): Banner? {
        return bannerJpaRepository.findByIsPostAndStatus(isPost, Status.active)?.let { bannerMapper.toDomain(it) }
    }

    override fun save(banner: Banner) {
        val bannerEntity = bannerMapper.toEntity(banner)
        bannerJpaRepository.save(bannerEntity)
    }

    override fun findAll(): List<Banner> {
        return bannerJpaRepository.findAllByStatus(Status.active).map { bannerMapper.toDomain(it) }
    }

    override fun findById(id: Long): Banner? {
        return bannerJpaRepository.findByIdAndStatus(id, Status.active)?.let { bannerMapper.toDomain(it) }
    }

    override fun delete(id: Long) {
        bannerJpaRepository.deleteById(id)
    }

}