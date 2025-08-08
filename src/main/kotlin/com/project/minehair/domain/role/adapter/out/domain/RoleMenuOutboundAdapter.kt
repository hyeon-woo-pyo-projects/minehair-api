package com.project.minehair.domain.role.adapter.out.domain

import com.project.minehair.domain.menu.adapter.`in`.domain.MenuCommandAdapter
import com.project.minehair.domain.menu.adapter.`in`.domain.MenuQueryAdapter
import com.project.minehair.domain.menu.adapter.out.persistence.MenuMapper
import com.project.minehair.domain.menu.domain.Menu
import com.project.minehair.domain.role.adapter.`in`.query.RoleQueryAdapter
import com.project.minehair.domain.role.application.port.out.domain.RoleMenuDomainPort
import com.project.minehair.global.domain.inter.InterDomainMenuInfo
import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import org.springframework.stereotype.Component

@Component
class RoleMenuOutboundAdapter(
    private val menuQueryAdapter: MenuQueryAdapter,
    private val menuCommandAdapter: MenuCommandAdapter,
    private val roleQueryAdapter: RoleQueryAdapter,
    private val menuMapper: MenuMapper
) : RoleMenuDomainPort {

    override fun getMenusByIds(menuIds: List<Long>): List<InterDomainMenuInfo> {
        if (menuIds.isEmpty()) {
            return emptyList()
        }

        return menuQueryAdapter.findMenusByIds(menuIds)
            .map { menu ->
                InterDomainMenuInfo(
                    id = menu.id,
                    parentId = menu.parentId,
                    name = menu.name,
                    path = menu.path,
                    imageUrl = menu.imageUrl,
                    isVisible = menu.isVisible,
                    menuType = menu.menuType,
                    orderNo = menu.orderNo,
                )
            }
    }

    override fun getMenuById(menuId: Long): InterDomainMenuInfo? {
        return try {
            val menu = menuQueryAdapter.findMenuById(menuId)
            InterDomainMenuInfo(
                id = menu.id,
                parentId = menu.parentId,
                name = menu.name,
                path = menu.path,
                imageUrl = menu.imageUrl,
                isVisible = menu.isVisible,
                menuType = menu.menuType,
                orderNo = menu.orderNo,
            )
        } catch (e: Exception) {
            // Menu가 없는 경우 null 반환
            null
        }
    }

    override fun getRoleByCode(roleCode: String): InterDomainRoleInfo? {
        return roleQueryAdapter.getRoleByCode(roleCode)?.let { role ->
            InterDomainRoleInfo(
                id = role.id,
                code = role.code,
                name = role.name,
            )
        }
    }

    override fun getMaxOrderNo(): Int {
        return menuQueryAdapter.getMaxOrderNo()
    }

    override fun createMenu(menuInfo: InterDomainMenuInfo): Menu {
        val createMenuCommand = menuMapper.toCreateCommand(menuInfo)
        return menuCommandAdapter.createMenu(createMenuCommand)
    }
}