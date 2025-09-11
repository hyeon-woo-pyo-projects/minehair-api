package com.project.minehair.domain.address.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "map_address")
class MapAddressJpaEntity(

    @Column(name = "lat")
    val lat: Double,

    @Column(name = "lng")
    val lng: Double,

    @Column(name = "address")
    val address: String,

    @Column(name = "detail_address")
    val detailAddress: String?,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}