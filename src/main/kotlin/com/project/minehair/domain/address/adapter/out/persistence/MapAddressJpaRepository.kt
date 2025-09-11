package com.project.minehair.domain.address.adapter.out.persistence

import com.project.minehair.global.enums.Status
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MapAddressJpaRepository : JpaRepository<MapAddressJpaEntity, Long> {

    fun findAllByStatus(stats: Status): List<MapAddressJpaEntity>

    fun findByIdAndStatus(id: Long, status: Status): MapAddressJpaEntity

}