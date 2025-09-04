package com.project.minehair.domain.logo.application.port.`in`

import com.project.minehair.domain.logo.adapter.`in`.web.dto.CreateLogoRequest
import com.project.minehair.domain.logo.adapter.`in`.web.dto.LogoResponse
import com.project.minehair.domain.logo.adapter.`in`.web.dto.UpdateLogoRequest

interface LogoUseCase {

    /**
     * 리스트 조회
     */
    fun getLogoList(): List<LogoResponse>

    /**
     * 상세 조회
     */
    fun getLogoDetails(id: Long): LogoResponse

    /**
     * 생성
     */
    fun createLogo(request: CreateLogoRequest): LogoResponse

    /**
     * 수정
     */
    fun updateLogo(id: Long, request: UpdateLogoRequest): LogoResponse

    /**
     * 삭제
     */
    fun deleteLogo(id: Long): LogoResponse

}