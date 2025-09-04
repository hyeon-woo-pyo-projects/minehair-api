package com.project.minehair.domain.logo.adapter.out.persistence

import com.project.minehair.domain.logo.application.port.out.LogoPersistencePort
import com.project.minehair.domain.logo.domain.Logo
import com.project.minehair.domain.logo.domain.LogoMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class LogoPersistenceAdapter(
    private val logoJpaRepository: LogoJpaRepository,
    private val logoMapper: LogoMapper
) : LogoPersistencePort {

    override fun getAllByMenuIdActiveStatus(): List<Logo> {
        return logoJpaRepository.findAllByAndStatus(Status.active)
            .let { logoMapper.toDomainList(it) }
    }

    override fun getById(id: Long): Logo {
        return logoJpaRepository.findById(id).get()
            .let { logoMapper.toDomain(it) }
    }

    override fun save(domain: Logo): Logo {
        return logoJpaRepository.save(logoMapper.toEntity(domain))
            .let { logoMapper.toDomain(it) }
    }

}