package com.project.minehair.domain.contents.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SnsPlatformJpaRepository : JpaRepository<SnsPlatformJpaEntity, Long> {

    fun findAllByAndStatus(status: Status): List<SnsPlatformJpaEntity>

    fun findTopByOrderByOrderNoDesc(): SnsPlatformJpaEntity?

}