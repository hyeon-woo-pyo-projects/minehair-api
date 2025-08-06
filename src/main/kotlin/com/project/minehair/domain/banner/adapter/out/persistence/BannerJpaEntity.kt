package com.project.minehair.domain.banner.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "banner")
class BannerJpaEntity (

    @Column(name = "content")
    val content: String,

    @Column(name = "color")
    val color: String,

    @Column(name = "text_color")
    val textColor: String,

    @Column(name = "link")
    val link: String,

    @Column(name = "image_url")
    val imageUrl: String?,

    @Column(name = "is_post")
    val isPost: Boolean,

    // BaseJpaEntity 필드들 override
    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}