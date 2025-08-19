package com.project.minehair.domain.home.application.port.`in`

import com.project.minehair.domain.home.adapter.`in`.web.dto.CreateHomeSlideRequest
import com.project.minehair.domain.home.adapter.`in`.web.dto.HomeSlideResponse

interface HomeSlideUseCase {

    /**
     * 홈 슬라이드 리스트 조회
     */
    fun getHomeSlideList(): List<HomeSlideResponse>

    /**
     * 홈 슬라이드 생성
     */
    fun createHomeSlide(request: CreateHomeSlideRequest): HomeSlideResponse

    /**
     * 홈 슬라이드 삭제
     */
    fun deleteHomeSlide(id: Long): HomeSlideResponse

}