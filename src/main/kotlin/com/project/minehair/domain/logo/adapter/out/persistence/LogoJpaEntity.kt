package com.project.minehair.domain.logo.adapter.out.persistence

import com.project.minehair.domain.logo.domain.LogoType
import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "logo")
class LogoJpaEntity(

    @Column(name = "logo_type", nullable = false)
    val logoType: LogoType,

    @Column(name = "description", nullable = true)
    val description: String?,

    @Column(name = "image_url", nullable = false)
    val imageUrl: String,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt)
