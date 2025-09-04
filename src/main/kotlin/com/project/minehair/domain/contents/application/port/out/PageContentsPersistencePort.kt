package com.project.minehair.domain.contents.application.port.out

import com.project.minehair.domain.contents.domain.PageContents

interface PageContentsPersistencePort {

    /**
     * menuId로 조회한 활성 리스트
     */
    fun getAllByMenuIdActiveStatus(menuId: Long): List<PageContents>

    /**
     * id로 조회
     */
    fun getById(id: Long): PageContents

    /**
     * max order_no 조회
     */
    fun getMaxOrderNo(): Int

    /**
     * 생성
     */
    fun save(domain: PageContents): PageContents

}