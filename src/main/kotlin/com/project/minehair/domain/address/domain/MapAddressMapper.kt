package com.project.minehair.domain.address.domain

import com.project.minehair.domain.address.adapter.`in`.web.dto.CreateMapAddressRequest
import com.project.minehair.domain.address.adapter.`in`.web.dto.MapAddressResponse
import com.project.minehair.domain.address.adapter.out.persistence.MapAddressJpaEntity
import org.springframework.stereotype.Component

@Component
class MapAddressMapper {

    fun toDomain(entity: MapAddressJpaEntity): MapAddress {
        return MapAddress(
            id = entity.id,
            lat = entity.lat,
            lng = entity.lng,
            address = entity.address,
            detailAddress = entity.detailAddress,
        )
    }

    fun toDomain(request: CreateMapAddressRequest): MapAddress {
        return MapAddress(
            id = null,
            lat = request.lat,
            lng = request.lng,
            address = request.address,
            detailAddress = request.detailAddress,
        )
    }

    fun toDomainList(entityList: List<MapAddressJpaEntity>): List<MapAddress> {
        return entityList.map { toDomain(it) }
    }

    fun toResponse(domain: MapAddress): MapAddressResponse {
        return MapAddressResponse(
            id = domain.id!!,
            lat = domain.lat,
            lng = domain.lng,
            address = domain.address,
            detailAddress = domain.detailAddress,
        )
    }

    fun toResponseList(domainList: List<MapAddress>): List<MapAddressResponse> {
        return domainList.map { toResponse(it) }
    }

    fun toEntity(domain: MapAddress): MapAddressJpaEntity {
        return MapAddressJpaEntity(
            id = domain.id,
            lat = domain.lat,
            lng = domain.lng,
            address = domain.address,
            detailAddress = domain.detailAddress,
            status = domain.status,
            updatedId = domain.updatedId,
        )
    }

}