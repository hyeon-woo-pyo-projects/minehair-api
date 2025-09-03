package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.domain.contens.application.port.out.SnsPlatformPersistencePort
import com.project.minehair.domain.contens.domain.SnsPlatform
import com.project.minehair.domain.contens.domain.SnsPlatformMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class SnsPlatformPersistenceAdapter(
    private val snsPlatformJpaRepository: SnsPlatformJpaRepository,
    private val snsPlatformMapper: SnsPlatformMapper
) : SnsPlatformPersistencePort {

    override fun getAllByMenuIdActiveStatus(): List<SnsPlatform> {
        return snsPlatformJpaRepository.findAllByAndStatus(Status.active)
            .let { snsPlatformMapper.toDomainList(it) }
    }

    override fun getById(id: Long): SnsPlatform {
        return snsPlatformJpaRepository.findById(id).get()
            .let { snsPlatformMapper.toDomain(it) }
    }

    override fun getMaxOrderNo(): Int {
        return snsPlatformJpaRepository.findTopByOrderByOrderNoDesc()?.orderNo ?: 0
    }

    override fun save(domain: SnsPlatform): SnsPlatform {
        return snsPlatformJpaRepository.save(snsPlatformMapper.toEntity(domain))
            .let { snsPlatformMapper.toDomain(it) }
    }

}