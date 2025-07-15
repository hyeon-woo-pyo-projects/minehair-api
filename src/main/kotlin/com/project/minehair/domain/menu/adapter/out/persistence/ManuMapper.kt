package com.project.minehair.domain.menu.adapter.out.persistence

import com.project.minehair.domain.menu.domain.Menu
import com.project.minehair.domain.menu.domain.MenuStatus
import org.springframework.stereotype.Component

// MenuMapper.kt - 도메인 ↔ JPA 매퍼
@Component
class MenuMapper {

    fun toDomain(entity: MenuJpaEntity): Menu {
        return Menu(
            id = entity.id,
            parentId = entity.parentId,
            name = entity.name,
            path = entity.path,
            orderNo = entity.orderNo,
            visible = entity.visible,
            status = MenuStatus.valueOf(entity.status.uppercase()),
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toEntity(domain: Menu): MenuJpaEntity {
        return MenuJpaEntity(
            id = domain.id,
            parentId = domain.parentId,
            name = domain.name,
            path = domain.path,
            orderNo = domain.orderNo,
            visible = domain.visible,
            status = domain.status.name.lowercase(),
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }
}