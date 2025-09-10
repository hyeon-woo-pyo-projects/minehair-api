package com.project.minehair.domain.user.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDate
import java.time.LocalDateTime

@Entity
@Table(name = "users")
class UserJpaEntity(

    @Column(name = "role_id")
    val roleId: Long,

    @Column(name = "user_id")
    val userId: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "name")
    val name: String,

    @Column(name = "phone")
    val phone: String,

    @Column(name = "phone_hash")
    val phoneHash: String,

    @Column(name = "email")
    val email: String?,

    @Column(name = "birth_date")
    val birthDate: LocalDate,

    // BaseJpaEntity 필드들 override
    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}