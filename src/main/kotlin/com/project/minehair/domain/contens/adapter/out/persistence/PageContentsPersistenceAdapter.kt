package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.domain.contens.application.port.out.PageContentsPersistencePort
import com.project.minehair.domain.contens.domain.PageContents
import com.project.minehair.domain.contens.domain.PageContentsMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class PageContentsPersistenceAdapter(
    private val pageContentsJpaRepository: PageContentsJpaRepository,
    private val pageContentsMapper: PageContentsMapper
) : PageContentsPersistencePort {

    override fun getAllByMenuIdActiveStatus(menuId: Long): List<PageContents> {
        return pageContentsJpaRepository.findAllByMenuIdAndStatus(menuId, Status.active)
            .let { pageContentsMapper.toDomainList(it) }
    }

    override fun getById(id: Long): PageContents {
        return pageContentsJpaRepository.findById(id).get()
            .let { pageContentsMapper.toDomain(it) }
    }

    override fun getMaxOrderNo(): Int {
        return pageContentsJpaRepository.findTopByOrderByOrderNoDesc()?.orderNo ?: 0
    }

    override fun save(domain: PageContents): PageContents {
        return pageContentsJpaRepository.save(pageContentsMapper.toEntity(domain))
            .let { pageContentsMapper.toDomain(it) }
    }

}