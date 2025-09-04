package com.project.minehair.domain.contents.application.port.out

import com.project.minehair.domain.contents.domain.EventPageContents

interface EventPageContentsPersistencePort {

    /**
     * 활성 리스트 조회
     */
    fun getAllByMenuIdActiveStatus(): List<EventPageContents>

    /**
     * id로 조회
     */
    fun getById(id: Long): EventPageContents

    /**
     * max order_no 조회
     */
    fun getMaxOrderNo(): Int

    /**
     * 생성
     */
    fun save(domain: EventPageContents): EventPageContents

}