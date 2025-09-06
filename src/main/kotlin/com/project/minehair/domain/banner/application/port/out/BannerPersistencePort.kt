package com.project.minehair.domain.banner.application.port.out

import com.project.minehair.domain.banner.domain.Banner

interface BannerPersistencePort {

    /**
     * 배너 리스트 조회
     */
    fun findAllActiveStatus(): List<Banner>

    /**
     * 배너 상세 조회
     */
    fun findById(id: Long): Banner

    /**
     * 배너 저장
     */
    fun save(banner: Banner): Banner

}