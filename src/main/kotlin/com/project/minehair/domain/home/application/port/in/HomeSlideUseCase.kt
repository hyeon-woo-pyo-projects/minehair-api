package com.project.minehair.domain.home.application.port.`in`

import com.project.minehair.domain.home.adapter.`in`.web.dto.HomeSlideResponse

interface HomeSlideUseCase {

    /**
     * 홈 슬라이드 리스트 조회
     */
    fun getHomeSlideList(): List<HomeSlideResponse>

}