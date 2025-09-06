package com.project.minehair.domain.contents.application.serivce

import com.project.minehair.domain.contents.adapter.`in`.web.dto.CreateEventPageContentsRequest
import com.project.minehair.domain.contents.adapter.`in`.web.dto.EventPageContentsResponse
import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdateEventPageContentsRequest
import com.project.minehair.domain.contents.application.port.`in`.EventPageContentsUseCase
import com.project.minehair.domain.contents.application.port.out.EventPageContentsPersistencePort
import com.project.minehair.domain.contents.domain.EventPageContentsMapper
import com.project.minehair.domain.contents.domain.EventPageContentsType
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class EventPageContentsService(
    private val eventPageContentsPersistencePort: EventPageContentsPersistencePort,
    private val eventPageContentsMapper: EventPageContentsMapper
) : EventPageContentsUseCase {

    override fun getEventPageContentsList(contentsType: EventPageContentsType): List<EventPageContentsResponse> {
        return eventPageContentsMapper.toResponseList(eventPageContentsPersistencePort.getAllByMenuIdActiveStatus()
            .filter { it.contentsType == contentsType }
        )
    }

    override fun getEventPageContentsDetails(id: Long): EventPageContentsResponse {
        return eventPageContentsMapper.toResponse(eventPageContentsPersistencePort.getById(id))
    }

    @Transactional
    override fun createEventPageContents(request: CreateEventPageContentsRequest): EventPageContentsResponse {
        val domainForCreate = eventPageContentsMapper.toDomain(request)
            .updateOrderNo(this.getNextOrderNo())
        return eventPageContentsMapper.toResponse(eventPageContentsPersistencePort.save(domainForCreate))
    }

    @Transactional
    override fun updateEventPageContents(id: Long, request: UpdateEventPageContentsRequest): EventPageContentsResponse {
        val domainForUpdate = eventPageContentsPersistencePort.getById(id)
            .updateFrom(request)
        return eventPageContentsMapper.toResponse(eventPageContentsPersistencePort.save(domainForUpdate))
    }

    @Transactional
    override fun deleteEventPageContents(id: Long): EventPageContentsResponse {
        val domainForDelete = eventPageContentsPersistencePort.getById(id)
            .delete()
        return eventPageContentsMapper.toResponse(eventPageContentsPersistencePort.save(domainForDelete))
    }

    // ---------- [private method] -----------

    private fun getNextOrderNo(): Int {
        return eventPageContentsPersistencePort.getMaxOrderNo().plus(1)
    }

}