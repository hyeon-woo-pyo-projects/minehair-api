package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EventPageContentsJpaRepository : JpaRepository<EventPageContentsJpaEntity, Long> {

    fun findAllByAndStatus(status: Status): List<EventPageContentsJpaEntity>

    fun findTopByOrderByOrderNoDesc(): EventPageContentsJpaEntity?

}