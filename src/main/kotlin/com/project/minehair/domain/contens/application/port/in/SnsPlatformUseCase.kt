package com.project.minehair.domain.contens.application.port.`in`

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateSnsPlatformRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.SnsPlatformResponse
import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateSnsPlatformRequest

interface SnsPlatformUseCase {

    /**
     * 리스트 조회
     */
    fun getSnsPlatformList(): List<SnsPlatformResponse>

    /**
     * 상세 조회
     */
    fun getSnsPlatformDetails(id: Long): SnsPlatformResponse

    /**
     * 생성
     */
    fun createSnsPlatform(request: CreateSnsPlatformRequest): SnsPlatformResponse

    /**
     * 수정
     */
    fun updateSnsPlatform(id: Long, request: UpdateSnsPlatformRequest): SnsPlatformResponse

    /**
     * 삭제
     */
    fun deleteSnsPlatform(id: Long): SnsPlatformResponse

}