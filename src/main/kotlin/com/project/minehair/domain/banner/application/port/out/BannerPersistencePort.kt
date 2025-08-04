package com.project.minehair.domain.banner.application.port.out

import com.project.minehair.domain.banner.domain.Banner

interface BannerPersistencePort {

    /**
     * 게시상태의 배너 조회
     */
    fun findByIsPost(isPost: Boolean): Banner?

    /**
     * 배너 저장
     */
    fun save(banner: Banner)

    /**
     * 배너 리스트 조회
     */
    fun findAll(): List<Banner>

    /**
     * 배너 상세 조회
     */
    fun findById(id: Long): Banner?

    /**
     * 배너 삭제
     */
    fun delete(id: Long)
}