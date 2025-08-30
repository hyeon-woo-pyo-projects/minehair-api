package com.project.minehair.domain.contens.application.port.`in`

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateMenuContentsRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.MenuContentsResponse
import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateMenuContentsRequest

interface MenuContentsUseCase {

    /**
     * 리스트 조회
     */
    fun getMenuContentsList(menuId: Long): List<MenuContentsResponse>

    /**
     * 상세 조회
     */
    fun getMenuContentsDetails(id: Long): MenuContentsResponse

    /**
     * 생성
     */
    fun createMenuContents(request: CreateMenuContentsRequest): MenuContentsResponse

    /**
     * 수정
     */
    fun updateMenuContents(id: Long, request: UpdateMenuContentsRequest): MenuContentsResponse

    /**
     * 삭제
     */
    fun deleteMenuContents(id: Long): MenuContentsResponse

}