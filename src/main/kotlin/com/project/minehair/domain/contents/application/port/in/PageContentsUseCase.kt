package com.project.minehair.domain.contents.application.port.`in`

import com.project.minehair.domain.contents.adapter.`in`.web.dto.CreatePageContentsRequest
import com.project.minehair.domain.contents.adapter.`in`.web.dto.PageContentsResponse
import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdatePageContentsRequest
import com.project.minehair.domain.contents.domain.ContentsType

interface PageContentsUseCase {

    /**
     * 리스트 조회
     */
    fun getPageContentsList(menuId: Long): List<PageContentsResponse>

    /**
     * contentsType으로 리스트 조회
     */
    fun getPageContentsList(contentsType: ContentsType): List<PageContentsResponse>

    /**
     * 상세 조회
     */
    fun getPageContentsDetails(id: Long): PageContentsResponse

    /**
     * 생성
     */
    fun createPageContents(request: CreatePageContentsRequest): PageContentsResponse

    /**
     * 수정
     */
    fun updatePageContents(id: Long, request: UpdatePageContentsRequest): PageContentsResponse

    /**
     * 삭제
     */
    fun deletePageContents(id: Long): PageContentsResponse

}