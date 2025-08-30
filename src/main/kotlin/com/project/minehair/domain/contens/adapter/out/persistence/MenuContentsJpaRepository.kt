package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MenuContentsJpaRepository : JpaRepository<MenuContentsJpaEntity, Long> {

    fun findAllByMenuIdAndStatus(menuId: Long, status: Status): List<MenuContentsJpaEntity>

    fun findTopByOrderByOrderNoDesc(): MenuContentsJpaEntity?
}