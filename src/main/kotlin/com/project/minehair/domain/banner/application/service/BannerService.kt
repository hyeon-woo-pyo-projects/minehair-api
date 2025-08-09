package com.project.minehair.domain.banner.application.service

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerCreateRequest
import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerUpdateRequest
import com.project.minehair.domain.banner.application.port.`in`.BannerUseCase
import com.project.minehair.domain.banner.application.port.out.BannerPersistencePort
import com.project.minehair.domain.banner.domain.Banner
import com.project.minehair.domain.banner.domain.BannerMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class BannerService(
    private val bannerPersistencePort: BannerPersistencePort,
    private val bannerMapper: BannerMapper
) : BannerUseCase {

    /**
     * 게시상태의 배너 조회
     */
    override fun getPostBanner(): BannerResponse {
        return bannerPersistencePort.findByIsPost(true)?.let { banner ->
            BannerResponse(
                id = banner.id,
                content = banner.content,
                color = banner.color,
                textColor = banner.textColor,
                link = banner.link,
                imageUrl = banner.imageUrl,
                isPost = banner.isPost
            )
        } ?: BannerResponse(
            id = null,
            content = "",
            color = "",
            textColor = "",
            link = "",
            imageUrl = null,
            isPost = false
        )
    }

    /**
     * 배너 생성
     */
    @Transactional(readOnly = false)
    override fun createBanner(request: BannerCreateRequest) {
        val banner = Banner.of(
            content = request.content,
            color = request.color,
            textColor = request.textColor,
            link = request.link,
            imageUrl = request.imageUrl,
        )
        bannerPersistencePort.save(banner)
    }

    /**
     * 배너 리스트 조회
     */
    override fun getBannersList(): List<BannerResponse> {
        return bannerPersistencePort.findAll().map { banner ->
            BannerResponse(
                id = banner.id,
                content = banner.content,
                color = banner.color,
                textColor = banner.textColor,
                link = banner.link,
                imageUrl = banner.imageUrl,
                isPost = banner.isPost
            )
        }
    }

    /**
     * 배너 상세 조회
     */
    override fun getBannerById(id: Long): BannerResponse {
        return bannerPersistencePort.findById(id)?.let { banner ->
            BannerResponse(
                id = banner.id,
                content = banner.content,
                color = banner.color,
                textColor = banner.textColor,
                link = banner.link,
                imageUrl = banner.imageUrl,
                isPost = banner.isPost
            )
        } ?: throw BusinessException(ErrorCode.NOT_FOUND, "Banner with id $id not found")
    }

    /**
     * 배너 수정
     */
    @Transactional(readOnly = false)
    override fun updateBanner(id: Long, request: BannerUpdateRequest) {
        val banner = bannerPersistencePort.findById(id)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "Banner with id $id not found")

        val updatedBanner = banner.update(
            content = request.content,
            color = request.color,
            textColor = request.textColor,
            link = request.link,
            imageUrl = request.imageUrl,
            isPost = request.isPost
        )

        bannerPersistencePort.save(updatedBanner)
    }

    /**
     * 배너 삭제
     */
    @Transactional(readOnly = false)
    override fun deleteBanner(id: Long) {
        val banner = bannerPersistencePort.findById(id)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "Banner with id $id not found")

        banner.delete()
        bannerPersistencePort.save(banner)
    }

    // 게시 상태의 배너가 있는지 확인
    private fun getPostBannerExists(): Boolean {
        return bannerPersistencePort.findByIsPost(true) != null
    }
}