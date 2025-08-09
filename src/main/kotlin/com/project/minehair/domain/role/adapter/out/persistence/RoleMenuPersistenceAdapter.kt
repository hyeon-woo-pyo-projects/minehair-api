package com.project.minehair.domain.role.adapter.out.persistence

import com.project.minehair.domain.role.application.port.out.persistence.RoleMenuPersistencePort
import com.project.minehair.domain.role.domain.RoleMenu
import com.project.minehair.global.enums.Status
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class RoleMenuPersistenceAdapter(
    private val roleMenuJpaRepository: RoleMenuJpaRepository,
    private val roleMenuMapper: RoleMenuMapper
) : RoleMenuPersistencePort {

    override fun findByRoleId(roleId: Long): List<RoleMenu> {
        return roleMenuJpaRepository.findByRoleIdAndStatus(roleId, Status.active)
            .map { roleMenuMapper.toDomain(it) }
    }

    override fun findByRoleIdAndMenuId(roleId: Long, menuId: Long): RoleMenu? {
        return roleMenuJpaRepository.findByRoleIdAndMenuId(roleId, menuId)
            ?.let { roleMenuMapper.toDomain(it) }
    }

    override fun saveRoleMenus(roleId: Long, menuIds: List<Long>) {
        val entities = menuIds.map { menuId ->
            RoleMenuJpaEntity(
                id = null,
                roleId = roleId,
                menuId = menuId,
                status = Status.active,
                createdId = 1L, // TODO: 현재 사용자 ID로 변경
                createdAt = LocalDateTime.now(),
                updatedId = 1L, // TODO: 현재 사용자 ID로 변경
                updatedAt = null
            )
        }
        roleMenuJpaRepository.saveAll(entities)
    }

    override fun deleteByRoleId(roleId: Long) {
        roleMenuJpaRepository.deleteByRoleId(roleId)
    }

    override fun deleteByRoleIdAndMenuId(roleId: Long, menuId: Long) {
        roleMenuJpaRepository.deleteByRoleIdAndMenuId(roleId, menuId)
    }

    override fun existsByRoleIdAndMenuId(roleId: Long, menuId: Long): Boolean {
        return roleMenuJpaRepository.existsByRoleIdAndMenuId(roleId, menuId)
    }

    override fun findByMenuId(menuId: Long): List<RoleMenu> {
        return roleMenuJpaRepository.findByMenuId(menuId)
            .map { roleMenuMapper.toDomain(it) }
    }

    override fun save(roleMenu: RoleMenu): RoleMenu {
        val entity = roleMenuMapper.toEntity(roleMenu)
        val savedEntity = roleMenuJpaRepository.save(entity)
        return roleMenuMapper.toDomain(savedEntity)
    }
}