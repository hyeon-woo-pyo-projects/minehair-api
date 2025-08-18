package com.project.minehair.domain.home.application.port.out

import com.project.minehair.domain.home.domain.HomeSlide

interface HomeSlidePersistencePort {

    /**
     * 홈 슬라이드 목록 조회
     */
    fun findAll(): List<HomeSlide>

}