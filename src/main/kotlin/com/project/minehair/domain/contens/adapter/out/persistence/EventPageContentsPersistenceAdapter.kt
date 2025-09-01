package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.domain.contens.application.port.out.EventPageContentsPersistencePort
import com.project.minehair.domain.contens.application.port.out.PageContentsPersistencePort
import com.project.minehair.domain.contens.domain.EventPageContents
import com.project.minehair.domain.contens.domain.EventPageContentsMapper
import com.project.minehair.domain.contens.domain.PageContents
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class EventPageContentsPersistenceAdapter(
    private val eventPageContentsJpaRepository: EventPageContentsJpaRepository,
    private val eventPageContentsMapper: EventPageContentsMapper
) : EventPageContentsPersistencePort {

    override fun getAllByMenuIdActiveStatus(): List<EventPageContents> {
        return eventPageContentsJpaRepository.findAllByAndStatus(Status.active)
            .let { eventPageContentsMapper.toDomainList(it) }
    }

    override fun getById(id: Long): EventPageContents {
        return eventPageContentsJpaRepository.findById(id).get()
            .let { eventPageContentsMapper.toDomain(it) }
    }

    override fun getMaxOrderNo(): Int {
        return eventPageContentsJpaRepository.findTopByOrderByOrderNoDesc()?.orderNo ?: 0
    }

    override fun save(domain: EventPageContents): EventPageContents {
        return eventPageContentsJpaRepository.save(eventPageContentsMapper.toEntity(domain))
            .let { eventPageContentsMapper.toDomain(it) }
    }

}