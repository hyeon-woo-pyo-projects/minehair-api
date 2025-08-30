package com.project.minehair.domain.contens.application.serivce

import com.project.minehair.domain.contens.adapter.`in`.web.dto.CreateMenuContentsRequest
import com.project.minehair.domain.contens.adapter.`in`.web.dto.MenuContentsResponse
import com.project.minehair.domain.contens.adapter.`in`.web.dto.UpdateMenuContentsRequest
import com.project.minehair.domain.contens.application.port.`in`.MenuContentsUseCase
import com.project.minehair.domain.contens.application.port.out.MenuContentsPersistencePort
import com.project.minehair.domain.contens.domain.MenuContentsMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class MenuContentsService(
    private val menuContentsPersistencePort: MenuContentsPersistencePort,
    private val manuContentsMapper: MenuContentsMapper
) : MenuContentsUseCase {

    override fun getMenuContentsList(menuId: Long): List<MenuContentsResponse> {
        return manuContentsMapper.toResponseList(menuContentsPersistencePort.getAllByMenuIdActiveStatus(menuId))
    }

    override fun getMenuContentsDetails(id: Long): MenuContentsResponse {
        return manuContentsMapper.toResponse(menuContentsPersistencePort.getById(id))
    }

    @Transactional
    override fun createMenuContents(request: CreateMenuContentsRequest): MenuContentsResponse {
         val domainForCreate = manuContentsMapper.toDomain(request)
             .updateOrderNo(this.getNextOrderNo())
        return manuContentsMapper.toResponse(menuContentsPersistencePort.save(domainForCreate))
    }

    @Transactional
    override fun updateMenuContents(id: Long, request: UpdateMenuContentsRequest): MenuContentsResponse {
        val domainForUpdate = menuContentsPersistencePort.getById(id)
            .updateFrom(request)
        return manuContentsMapper.toResponse(menuContentsPersistencePort.save(domainForUpdate))
    }

    @Transactional
    override fun deleteMenuContents(id: Long): MenuContentsResponse {
        val domainForDelete = menuContentsPersistencePort.getById(id)
            .delete()
        return manuContentsMapper.toResponse(menuContentsPersistencePort.save(domainForDelete))
    }

    // ---------- [private method] -----------

    private fun getNextOrderNo(): Int {
        return menuContentsPersistencePort.getMaxOrderNo().plus(1)
    }

}