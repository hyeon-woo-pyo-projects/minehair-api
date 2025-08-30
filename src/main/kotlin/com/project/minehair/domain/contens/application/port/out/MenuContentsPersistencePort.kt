package com.project.minehair.domain.contens.application.port.out

import com.project.minehair.domain.contens.adapter.`in`.web.dto.MenuContentsResponse
import com.project.minehair.domain.contens.domain.MenuContents

interface MenuContentsPersistencePort {

    /**
     * menuId로 조회한 활성 리스트
     */
    fun getAllByMenuIdActiveStatus(menuId: Long): List<MenuContents>

    /**
     * id로 조회
     */
    fun getById(id: Long): MenuContents

    /**
     * max order_no 조회
     */
    fun getMaxOrderNo(): Int

    /**
     * 생성
     */
    fun save(domain: MenuContents): MenuContents

}