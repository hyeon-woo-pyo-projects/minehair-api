package com.project.minehair.domain.banner.application.port.`in`

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.CreateBannerRequest
import com.project.minehair.domain.banner.adapter.`in`.web.dto.UpdateBannerRequest

interface BannerUseCase {

    /**
     * 배너 리스트 조회
     */
    fun getBannersList(): List<BannerResponse>

    /**
     * 배너 리스트 조회
     */
    fun getBannersDetails(id: Long): BannerResponse

    /**
     * 배너 생성
     */
    fun createBanner(request: CreateBannerRequest): BannerResponse

    /**
     * 배너 수정
     */
    fun updateBanner(id: Long, request: UpdateBannerRequest): BannerResponse

    /**
     * 배너 삭제
     */
    fun deleteBanner(id: Long): BannerResponse

}