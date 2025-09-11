package com.project.minehair.domain.contents.application.serivce

import com.project.minehair.domain.contents.adapter.`in`.web.dto.CreatePageContentsRequest
import com.project.minehair.domain.contents.adapter.`in`.web.dto.PageContentsResponse
import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdatePageContentsRequest
import com.project.minehair.domain.contents.application.port.`in`.PageContentsUseCase
import com.project.minehair.domain.contents.application.port.out.PageContentsPersistencePort
import com.project.minehair.domain.contents.domain.ContentsType
import com.project.minehair.domain.contents.domain.PageContentsMapper
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
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

    override fun getPageContentsList(contentsType: ContentsType): List<PageContentsResponse> {
        return pageContentsMapper.toResponseList(pageContentsPersistencePort.getAllByContentsType(contentsType))
    }

    override fun getPageContentsDetails(id: Long): PageContentsResponse {
        return pageContentsMapper.toResponse(pageContentsPersistencePort.getById(id))
    }

    @Transactional
    override fun createPageContents(request: CreatePageContentsRequest): PageContentsResponse {
        if (request.contentsType == ContentsType.CONSULTING_BACKGROUND) {
            val consultingBackGroundList = pageContentsPersistencePort.getAllByContentsType(request.contentsType)
            if (consultingBackGroundList.size > 1) {
                throw BusinessException(ErrorCode.FOUND, "상담 배경 이미지는 하나만 등록 가능합니다.")
            }
        }

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