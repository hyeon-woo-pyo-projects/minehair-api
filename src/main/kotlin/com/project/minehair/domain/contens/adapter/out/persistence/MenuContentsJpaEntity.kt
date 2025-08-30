package com.project.minehair.domain.contens.adapter.out.persistence

import com.project.minehair.domain.contens.domain.ContentsType
import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "menu_contents")
data class MenuContentsJpaEntity(

    @Column(name = "menu_id", nullable = false)
    val menuId: Long,

    @Column(name = "contents_type", nullable = false)
    val contentsType: ContentsType,

    @Column(name = "contents_url", nullable = false)
    val contentsUrl: String,

    @Column(name = "order_no", nullable = false)
    val orderNo: Int,

    // BaseJpaEntity 필드들 override
    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt)
