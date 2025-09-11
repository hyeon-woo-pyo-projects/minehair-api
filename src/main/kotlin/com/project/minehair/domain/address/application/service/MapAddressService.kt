package com.project.minehair.domain.address.application.service

import com.project.minehair.domain.address.adapter.`in`.web.dto.CreateMapAddressRequest
import com.project.minehair.domain.address.adapter.`in`.web.dto.MapAddressResponse
import com.project.minehair.domain.address.adapter.`in`.web.dto.UpdateMapAddressRequest
import com.project.minehair.domain.address.application.port.`in`.MapAddressUseCase
import com.project.minehair.domain.address.application.port.out.MapAddressPersistencePort
import com.project.minehair.domain.address.domain.MapAddressMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MapAddressService(
    private val mapAddressPersistencePort: MapAddressPersistencePort,
    private val mapAddressMapper: MapAddressMapper
) : MapAddressUseCase {

    override fun getMapAddressList(): List<MapAddressResponse> {
        return mapAddressPersistencePort.findAllActiveState()
            .let { mapAddressMapper.toResponseList(it) }
    }

    @Transactional
    override fun getMapAddressDetails(id: Long): MapAddressResponse {
        val mapAddress = mapAddressPersistencePort.findByIdActiveState(id)
        val updatedMapAddress = mapAddressPersistencePort.save(mapAddress)
        return mapAddressMapper.toResponse(updatedMapAddress)
    }

    @Transactional
    override fun createMapAddress(request: CreateMapAddressRequest): MapAddressResponse {
        val mapAddressForCreate = mapAddressMapper.toDomain(request)
        val createdMapAddress = mapAddressPersistencePort.save(mapAddressForCreate)
        return mapAddressMapper.toResponse(createdMapAddress)
    }

    @Transactional
    override fun updateMapAddress(id: Long, request: UpdateMapAddressRequest): MapAddressResponse {
        val mapAddress = mapAddressPersistencePort.findByIdActiveState(id)
        val mapAddressForUpdate = mapAddress.update(request)
        val updatedMapAddress = mapAddressPersistencePort.save(mapAddressForUpdate)
        return mapAddressMapper.toResponse(updatedMapAddress)
    }

    @Transactional
    override fun deleteMapAddress(id: Long): MapAddressResponse {
        val mapAddress = mapAddressPersistencePort.findByIdActiveState(id)
        val mapAddressForDelete = mapAddress.delete()
        val deletedMapAddress = mapAddressPersistencePort.save(mapAddressForDelete)
        return mapAddressMapper.toResponse(deletedMapAddress)
    }


}