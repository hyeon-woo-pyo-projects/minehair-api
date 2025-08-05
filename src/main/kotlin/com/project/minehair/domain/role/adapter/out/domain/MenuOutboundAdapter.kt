package com.project.minehair.domain.role.adapter.out.domain

import com.project.minehair.domain.menu.adapter.`in`.query.MenuQueryAdapter
import com.project.minehair.domain.role.adapter.`in`.query.RoleQueryAdapter
import com.project.minehair.domain.role.application.port.out.domain.MenuDomainPort
import com.project.minehair.global.domain.inter.InterDomainMenuInfo
import com.project.minehair.global.domain.inter.InterDomainRoleInfo
import org.springframework.stereotype.Component

/**
 * Menu 도메인과 통신하는 어댑터
 * MenuPort의 구현체로, 실제 Menu 도메인의 UseCase를 호출
 */
@Component
class MenuOutboundAdapter(
    private val menuQueryAdapter: MenuQueryAdapter,
    private val roleQueryAdapter: RoleQueryAdapter
) : MenuDomainPort {

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
                    orderNo = menu.orderNo,
                    visible = menu.visible
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
                orderNo = menu.orderNo,
                visible = menu.visible
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
}