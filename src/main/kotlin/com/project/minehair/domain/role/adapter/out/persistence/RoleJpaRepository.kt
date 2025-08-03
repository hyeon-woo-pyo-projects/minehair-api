package com.project.minehair.domain.role.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleJpaRepository : JpaRepository<RoleJpaEntity, Long> {

    fun findByCode(roleCode: String): RoleJpaEntity?

}