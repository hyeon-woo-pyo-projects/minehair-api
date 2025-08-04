package com.project.minehair.domain.banner.application.port.`in`

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerCreateRequest
import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerUpdateRequest

interface BannerUseCase {

    /**
     * 게시상태의 배너 조회
     */
    fun getPostBanner(): BannerResponse

    /**
     * 배너 생성
     */
    fun createBanner(request: BannerCreateRequest)

    /**
     * 배너 리스트 조회
     */
    fun getBannersList(): List<BannerResponse>

    /**
     * 배너 상세 조회
     */
    fun getBannerById(id: Long): BannerResponse

    /**
     * 배너 수정
     */
    fun updateBanner(id: Long, request: BannerUpdateRequest)

    /**
     * 배너 삭제
     */
    fun deleteBanner(id: Long)

}