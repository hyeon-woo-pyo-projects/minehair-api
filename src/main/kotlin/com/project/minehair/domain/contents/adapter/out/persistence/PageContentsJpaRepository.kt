package com.project.minehair.domain.contents.adapter.out.persistence

import com.project.minehair.domain.contents.domain.ContentsType
import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PageContentsJpaRepository : JpaRepository<PageContentsJpaEntity, Long> {

    fun findAllByContentsTypeAndStatus(contentsType: ContentsType, status: Status): List<PageContentsJpaEntity>

    fun findAllByMenuIdAndStatus(menuId: Long, status: Status): List<PageContentsJpaEntity>

    fun findTopByOrderByOrderNoDesc(): PageContentsJpaEntity?
}