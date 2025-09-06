package com.project.minehair.domain.banner.application.service

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.CreateBannerRequest
import com.project.minehair.domain.banner.adapter.`in`.web.dto.UpdateBannerRequest
import com.project.minehair.domain.banner.application.port.`in`.BannerUseCase
import com.project.minehair.domain.banner.application.port.out.BannerPersistencePort
import com.project.minehair.domain.banner.domain.BannerMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BannerService(
    private val bannerPersistencePort: BannerPersistencePort,
    private val bannerMapper: BannerMapper
) : BannerUseCase {

    /**
     * 배너 리스트 조회
     */
    override fun getBannersList(): List<BannerResponse> {
        return bannerMapper.toResponseList(bannerPersistencePort.findAllActiveStatus())
    }

    /**
     * 배너 상세 조회
     */
    override fun getBannersDetails(id: Long): BannerResponse {
        return bannerMapper.toResponse(bannerPersistencePort.findById(id))
    }

    /**
     * 배너 생성
     */
    @Transactional
    override fun createBanner(request: CreateBannerRequest): BannerResponse {
        val domainForCreate = bannerMapper.toDomain(request)
        return bannerMapper.toResponse(bannerPersistencePort.save(domainForCreate))
    }

    /**
     * 배너 수정
     */
    @Transactional
    override fun updateBanner(id: Long, request: UpdateBannerRequest): BannerResponse {
        val banner = bannerPersistencePort.findById(id)
        val bannerForUpdate = banner.updateFrom(request)
        return bannerMapper.toResponse(bannerPersistencePort.save(bannerForUpdate))
    }

    /**
     * 배너 삭제
     */
    @Transactional
    override fun deleteBanner(id: Long): BannerResponse {
        val banner = bannerPersistencePort.findById(id)
        val bannerForDelete = banner.delete()
        return bannerMapper.toResponse(bannerPersistencePort.save(bannerForDelete))
    }

}