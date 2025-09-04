package com.project.minehair.domain.logo.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LogoJpaRepository : JpaRepository<LogoJpaEntity, Long> {

    fun findAllByAndStatus(status: Status): List<LogoJpaEntity>

}