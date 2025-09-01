package com.project.minehair.domain.contens.application.serivce

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreatePageContentsRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.PageContentsResponse
import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdatePageContentsRequest
import com.project.minehair.domain.contens.application.port.`in`.PageContentsUseCase
import com.project.minehair.domain.contens.application.port.out.PageContentsPersistencePort
import com.project.minehair.domain.contens.domain.PageContentsMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class PageContentsService(
    private val pageContentsPersistencePort: PageContentsPersistencePort,
    private val pageContentsMapper: PageContentsMapper
) : PageContentsUseCase {

    override fun getPageContentsList(menuId: Long): List<PageContentsResponse> {
        return pageContentsMapper.toResponseList(pageContentsPersistencePort.getAllByMenuIdActiveStatus(menuId))
    }

    override fun getPageContentsDetails(id: Long): PageContentsResponse {
        return pageContentsMapper.toResponse(pageContentsPersistencePort.getById(id))
    }

    @Transactional
    override fun createPageContents(request: CreatePageContentsRequest): PageContentsResponse {
        val domainForCreate = pageContentsMapper.toDomain(request)
            .updateOrderNo(this.getNextOrderNo())
        return pageContentsMapper.toResponse(pageContentsPersistencePort.save(domainForCreate))
    }

    @Transactional
    override fun updatePageContents(id: Long, request: UpdatePageContentsRequest): PageContentsResponse {
        val domainForUpdate = pageContentsPersistencePort.getById(id)
            .updateFrom(request)
        return pageContentsMapper.toResponse(pageContentsPersistencePort.save(domainForUpdate))
    }

    @Transactional
    override fun deletePageContents(id: Long): PageContentsResponse {
        val domainForDelete = pageContentsPersistencePort.getById(id)
            .delete()
        return pageContentsMapper.toResponse(pageContentsPersistencePort.save(domainForDelete))
    }

    // ---------- [private method] -----------

    private fun getNextOrderNo(): Int {
        return pageContentsPersistencePort.getMaxOrderNo().plus(1)
    }

}