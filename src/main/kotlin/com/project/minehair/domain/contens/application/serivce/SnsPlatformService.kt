package com.project.minehair.domain.contens.application.serivce

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateSnsPlatformRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.SnsPlatformResponse
import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateSnsPlatformRequest
import com.project.minehair.domain.contens.application.port.`in`.SnsPlatformUseCase
import com.project.minehair.domain.contens.application.port.out.SnsPlatformPersistencePort
import com.project.minehair.domain.contens.domain.SnsPlatformMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class SnsPlatformService(
    private val snsPlatformPersistencePort: SnsPlatformPersistencePort,
    private val snsPlatformMapper: SnsPlatformMapper
) : SnsPlatformUseCase {

    override fun getSnsPlatformList(): List<SnsPlatformResponse> {
        return snsPlatformMapper.toResponseList(snsPlatformPersistencePort.getAllByMenuIdActiveStatus())
    }

    override fun getSnsPlatformDetails(id: Long): SnsPlatformResponse {
        return snsPlatformMapper.toResponse(snsPlatformPersistencePort.getById(id))
    }

    @Transactional
    override fun createSnsPlatform(request: CreateSnsPlatformRequest): SnsPlatformResponse {
        val nextOrderNo = if (request.orderNo == 0) {
            this.getNextOrderNo()
        } else {
            request.orderNo
        }
        val domainForCreate = snsPlatformMapper.toDomain(request)
            .updateOrderNo(nextOrderNo)
        return snsPlatformMapper.toResponse(snsPlatformPersistencePort.save(domainForCreate))
    }

    @Transactional
    override fun updateSnsPlatform(id: Long, request: UpdateSnsPlatformRequest): SnsPlatformResponse {
        val domainForUpdate = snsPlatformPersistencePort.getById(id)
            .updateFrom(request)
        return snsPlatformMapper.toResponse(snsPlatformPersistencePort.save(domainForUpdate))
    }

    @Transactional
    override fun deleteSnsPlatform(id: Long): SnsPlatformResponse {
        val domainForDelete = snsPlatformPersistencePort.getById(id)
            .delete()
        return snsPlatformMapper.toResponse(snsPlatformPersistencePort.save(domainForDelete))
    }

    // ---------- [private method] -----------

    private fun getNextOrderNo(): Int {
        return snsPlatformPersistencePort.getMaxOrderNo().plus(1)
    }

}