package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.domain.contens.application.port.out.MenuContentsPersistencePort
import com.project.minehair.domain.contens.domain.MenuContents
import com.project.minehair.domain.contens.domain.MenuContentsMapper
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component

@Component
class MenuContentsPersistenceAdapter(
    private val menuContentsJpaRepository: MenuContentsJpaRepository,
    private val menuContentsMapper: MenuContentsMapper
) : MenuContentsPersistencePort {

    override fun getAllByMenuIdActiveStatus(menuId: Long): List<MenuContents> {
        return menuContentsJpaRepository.findAllByMenuIdAndStatus(menuId, Status.active)
            .let { menuContentsMapper.toDomainList(it) }
    }

    override fun getById(id: Long): MenuContents {
        return menuContentsJpaRepository.findById(id).get()
            .let { menuContentsMapper.toDomain(it) }
    }

    override fun getMaxOrderNo(): Int {
        return menuContentsJpaRepository.findTopByOrderByOrderNoDesc()?.orderNo ?: 0
    }

    override fun save(domain: MenuContents): MenuContents {
        return menuContentsJpaRepository.save(menuContentsMapper.toEntity(domain))
            .let { menuContentsMapper.toDomain(it) }
    }

}