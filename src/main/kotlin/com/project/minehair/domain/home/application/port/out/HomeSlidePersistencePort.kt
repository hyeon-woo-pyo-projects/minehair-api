package com.project.minehair.domain.home.application.port.out

import com.project.minehair.domain.home.domain.HomeSlide

interface HomeSlidePersistencePort {

    /**
     * 홈 슬라이드 목록 조회
     */
    fun findAllActiveState(): List<HomeSlide>

    /**
     * 홈 슬라이드 저장
     */
    fun save(homeSlide: HomeSlide): HomeSlide

    /**
     * 홈 슬라이드 ID로 조회
     */
    fun findById(id: Long): HomeSlide?

}