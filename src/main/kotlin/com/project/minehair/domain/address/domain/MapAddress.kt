package com.project.minehair.domain.address.domain

import com.project.minehair.domain.address.adapter.`in`.web.dto.UpdateMapAddressRequest
import com.project.minehair.domain.board.adapter.`in`.web.dto.UpdateBoardReviewCategoryRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class MapAddress(

    val lat: Double,
    val lng: Double,
    val address: String,
    val detailAddress: String?,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    fun update(
        request: UpdateMapAddressRequest
    ): MapAddress {
        return this.copy(
            lat = request.lat,
            lng = request.lng,
            address = request.address,
            detailAddress = request.detailAddress,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }

    fun delete(): MapAddress {
        return this.copy(
            status = Status.deleted,
            updatedAt = LocalDateTime.now()
        )
    }
}
