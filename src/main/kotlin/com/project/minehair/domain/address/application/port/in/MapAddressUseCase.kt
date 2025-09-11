package com.project.minehair.domain.address.application.port.`in`

import com.project.minehair.domain.address.adapter.`in`.web.dto.CreateMapAddressRequest
import com.project.minehair.domain.address.adapter.`in`.web.dto.MapAddressResponse
import com.project.minehair.domain.address.adapter.`in`.web.dto.UpdateMapAddressRequest

interface MapAddressUseCase {

    /**
     * 지도 주소 조회
     */
    fun getMapAddressList(): List<MapAddressResponse>

    /**
     * 지도 주소 상세조회
     */
    fun getMapAddressDetails(id: Long): MapAddressResponse

    /**
     * 지도 주소 생성
     */
    fun createMapAddress(request: CreateMapAddressRequest): MapAddressResponse

    /**
     * 지도 주소 수정
     */
    fun updateMapAddress(id: Long, request: UpdateMapAddressRequest): MapAddressResponse

    /**
     * 지도 주소 삭제
     */
    fun deleteMapAddress(id: Long): MapAddressResponse

}