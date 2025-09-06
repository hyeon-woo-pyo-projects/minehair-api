package com.project.minehair.domain.contents.application.port.`in`

import com.project.minehair.domain.contents.adapter.`in`.web.dto.*
import com.project.minehair.domain.contents.domain.EventPageContentsType

interface EventPageContentsUseCase {

    /**
     * 리스트 조회
     */
    fun getEventPageContentsList(contentsType: EventPageContentsType): List<EventPageContentsResponse>

    /**
     * 상세 조회
     */
    fun getEventPageContentsDetails(id: Long): EventPageContentsResponse

    /**
     * 생성
     */
    fun createEventPageContents(request: CreateEventPageContentsRequest): EventPageContentsResponse

    /**
     * 수정
     */
    fun updateEventPageContents(id: Long, request: UpdateEventPageContentsRequest): EventPageContentsResponse

    /**
     * 삭제
     */
    fun deleteEventPageContents(id: Long): EventPageContentsResponse

}