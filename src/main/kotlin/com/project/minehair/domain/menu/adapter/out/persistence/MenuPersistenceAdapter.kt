package com.project.minehair.domain.menu.adapter.out.persistence

import com.project.minehair.domain.menu.application.port.out.MenuPersistencePort
import com.project.minehair.domain.menu.domain.Menu
import org.springframework.stereotype.Component

// MenuPersistenceAdapter.kt - 영속성 어댑터
@Component
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

    // MenuQueryService용 추가 메서드들 구현
    override fun findByIds(ids: List<Long>): List<Menu> {
        if (ids.isEmpty()) {
            return emptyList()
        }
        return menuJpaRepository.findAllById(ids)
            .map { menuMapper.toDomain(it) }
    }

    override fun findByParentId(parentId: Long): List<Menu> {
        return menuJpaRepository.findByParentIdOrderByOrderNo(parentId)
            .map { menuMapper.toDomain(it) }
    }

    override fun findActiveMenus(): List<Menu> {
        return menuJpaRepository.findByIsVisibleTrueOrderByOrderNo()
            .map { menuMapper.toDomain(it) }
    }

    override fun findByPath(path: String): Menu? {
        return menuJpaRepository.findByPath(path)
            ?.let { menuMapper.toDomain(it) }
    }

    override fun findMaxOrderNo(): Int? {
        return menuJpaRepository.findTopByOrderByOrderNoDesc()?.orderNo
    }
}