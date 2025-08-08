package com.project.minehair.domain.menu.adapter.out.persistence

import com.project.minehair.domain.menu.application.port.`in`.commnad.CreateMenuCommand
import com.project.minehair.domain.menu.domain.Menu
import com.project.minehair.global.domain.inter.InterDomainMenuInfo
import org.springframework.stereotype.Component
import java.time.LocalDateTime

// MenuMapper.kt - 도메인 ↔ JPA 매퍼
@Component
class MenuMapper {

    fun toDomain(entity: MenuJpaEntity): Menu {
        return Menu(
            id = entity.id,
            parentId = entity.parentId,
            name = entity.name,
            path = entity.path,
            imageUrl = entity.imageUrl,
            isVisible = entity.isVisible,
            menuType = entity.menuType,
            orderNo = entity.orderNo,
            status = entity.status,
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }

    fun toDomain(createMenuCommand: CreateMenuCommand): Menu {
        return Menu(
            id = null,
            parentId = createMenuCommand.parentId,
            name = createMenuCommand.name,
            path = createMenuCommand.path,
            imageUrl = createMenuCommand.imageUrl,
            isVisible = createMenuCommand.isVisible,
            menuType = createMenuCommand.menuType,
            orderNo = createMenuCommand.orderNo,
            createdId = 1L,
            createdAt = LocalDateTime.now(),
            updatedId = 0L,
            updatedAt = null
        )
    }

    fun toEntity(domain: Menu): MenuJpaEntity {
        return MenuJpaEntity(
            id = domain.id,
            parentId = domain.parentId,
            name = domain.name,
            path = domain.path,
            imageUrl = domain.imageUrl,
            isVisible = domain.isVisible,
            menuType = domain.menuType,
            orderNo = domain.orderNo,
            status = domain.status,
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }

    /**
     * Menu 도메인 엔티티를 도메인 간 통신용 객체로 변환
     * @param domain Menu 도메인 엔티티
     * @return 도메인 간 통신용 메뉴 정보
     */
    fun toInterDomainInfo(domain: Menu): InterDomainMenuInfo {
        return InterDomainMenuInfo(
            id = domain.id!!,
            parentId = domain.parentId,
            name = domain.name,
            path = domain.path,
            imageUrl = domain.imageUrl,
            isVisible = domain.isVisible,
            menuType = domain.menuType,
            orderNo = domain.orderNo,
        )
    }

    /**
     * Menu 도메인 엔티티 리스트를 도메인 간 통신용 객체 리스트로 변환
     * @param domains Menu 도메인 엔티티 리스트
     * @return 도메인 간 통신용 메뉴 정보 리스트
     */
    fun toInterDomainInfoList(domains: List<Menu>): List<InterDomainMenuInfo> {
        return domains.map { toInterDomainInfo(it) }
    }

    fun toCreateCommand(interDomainMenuInfo: InterDomainMenuInfo): CreateMenuCommand {
        return CreateMenuCommand(
            parentId = interDomainMenuInfo.parentId,
            name = interDomainMenuInfo.name,
            path = interDomainMenuInfo.path,
            imageUrl = interDomainMenuInfo.imageUrl,
            isVisible = interDomainMenuInfo.isVisible,
            menuType = interDomainMenuInfo.menuType,
            orderNo = interDomainMenuInfo.orderNo
        )
    }
}