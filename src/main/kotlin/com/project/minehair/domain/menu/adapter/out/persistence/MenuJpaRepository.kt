package com.project.minehair.domain.menu.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

// MenuJpaRepository.kt - Spring Data JPA Repository
interface MenuJpaRepository : JpaRepository<MenuJpaEntity, Long> {
    fun findByStatus(status: String): List<MenuJpaEntity>
    fun findByVisible(visible: Boolean): List<MenuJpaEntity>
    fun findByParentId(parentId: Long): List<MenuJpaEntity>
}