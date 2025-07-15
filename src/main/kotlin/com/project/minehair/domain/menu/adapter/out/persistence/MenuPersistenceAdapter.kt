package com.project.minehair.domain.menu.adapter.out.persistence

import com.project.minehair.domain.menu.application.port.out.MenuPersistencePort
import com.project.minehair.domain.menu.domain.Menu
import org.springframework.stereotype.Repository

// MenuPersistenceAdapter.kt - 영속성 어댑터
@Repository
class MenuPersistenceAdapter(
    private val menuJpaRepository: MenuJpaRepository,
    private val menuMapper: MenuMapper
) : MenuPersistencePort {

    override fun save(menu: Menu): Menu {
        val entity = menuMapper.toEntity(menu)
        val savedEntity = menuJpaRepository.save(entity)
        return menuMapper.toDomain(savedEntity)
    }

    override fun findById(id: Long): Menu? {
        return menuJpaRepository.findById(id)
            .map { menuMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findAll(): List<Menu> {
        return menuJpaRepository.findAll()
            .map { menuMapper.toDomain(it) }
    }

    override fun deleteById(id: Long) {
        menuJpaRepository.deleteById(id)
    }
}