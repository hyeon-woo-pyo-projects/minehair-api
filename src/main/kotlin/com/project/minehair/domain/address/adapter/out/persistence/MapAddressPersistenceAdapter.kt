package com.project.minehair.domain.address.adapter.out.persistence

import com.project.minehair.domain.address.application.port.out.MapAddressPersistencePort
import com.project.minehair.domain.address.domain.MapAddress
import com.project.minehair.domain.address.domain.MapAddressMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class MapAddressPersistenceAdapter(
    private val mapAddressJpaRepository: MapAddressJpaRepository,
    private val mapAddressMapper: MapAddressMapper
) : MapAddressPersistencePort {

    override fun findAllActiveState(): List<MapAddress> {
        val result = mapAddressJpaRepository.findAllByStatus(Status.active)
        return mapAddressMapper.toDomainList(result)
    }

    override fun findByIdActiveState(id: Long): MapAddress {
        return mapAddressJpaRepository.findByIdAndStatus(id, Status.active)
            .let { mapAddressMapper.toDomain(it) }
    }

    override fun save(mapAddress: MapAddress): MapAddress {
        val entityForCreate = mapAddressMapper.toEntity(mapAddress)
        val createdEntity = mapAddressJpaRepository.save(entityForCreate)
        return mapAddressMapper.toDomain(createdEntity)
    }

}