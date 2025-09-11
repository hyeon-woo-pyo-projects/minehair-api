package com.project.minehair.domain.address.application.port.out

import com.project.minehair.domain.address.domain.MapAddress

interface MapAddressPersistencePort {
    /**
     * QNA 게시판 조회 (페이지)
     */
    fun findAllActiveState(): List<MapAddress>

    /**
     * QNA 게시판 상세조회
     */
    fun findByIdActiveState(id: Long): MapAddress

    /**
     * QNA 게시판 생성
     */
    fun save(mapAddress: MapAddress): MapAddress

}