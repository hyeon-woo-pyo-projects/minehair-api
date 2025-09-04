package com.project.minehair.domain.logo.application.service

import com.project.minehair.domain.logo.adapter.`in`.web.dto.CreateLogoRequest
import com.project.minehair.domain.logo.adapter.`in`.web.dto.LogoResponse
import com.project.minehair.domain.logo.adapter.`in`.web.dto.UpdateLogoRequest
import com.project.minehair.domain.logo.application.port.`in`.LogoUseCase
import com.project.minehair.domain.logo.application.port.out.LogoPersistencePort
import com.project.minehair.domain.logo.domain.LogoMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class LogoService(
    private val logoPersistencePort: LogoPersistencePort,
    private val logoMapper: LogoMapper
) : LogoUseCase {

    override fun getLogoList(): List<LogoResponse> {
        return logoMapper.toResponseList(logoPersistencePort.getAllByMenuIdActiveStatus())
    }

    override fun getLogoDetails(id: Long): LogoResponse {
        return logoMapper.toResponse(logoPersistencePort.getById(id))
    }

    @Transactional
    override fun createLogo(request: CreateLogoRequest): LogoResponse {
        val domainForCreate = logoMapper.toDomain(request)
        return logoMapper.toResponse(logoPersistencePort.save(domainForCreate))
    }

    @Transactional
    override fun updateLogo(id: Long, request: UpdateLogoRequest): LogoResponse {
        val domainForUpdate = logoPersistencePort.getById(id)
            .updateFrom(request)
        return logoMapper.toResponse(logoPersistencePort.save(domainForUpdate))
    }

    @Transactional
    override fun deleteLogo(id: Long): LogoResponse {
        val domainForDelete = logoPersistencePort.getById(id)
            .delete()
        return logoMapper.toResponse(logoPersistencePort.save(domainForDelete))
    }

}