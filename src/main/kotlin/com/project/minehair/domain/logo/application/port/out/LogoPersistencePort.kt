package com.project.minehair.domain.logo.application.port.out

import com.project.minehair.domain.logo.domain.Logo

interface LogoPersistencePort {

    /**
     * 활성 리스트 조회
     */
    fun getAllByMenuIdActiveStatus(): List<Logo>

    /**
     * id로 조회
     */
    fun getById(id: Long): Logo

    /**
     * 생성
     */
    fun save(domain: Logo): Logo

}