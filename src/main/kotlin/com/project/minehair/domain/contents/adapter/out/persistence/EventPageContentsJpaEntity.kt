package com.project.minehair.domain.contents.adapter.out.persistence

import com.project.minehair.domain.contents.domain.EventPageContentsType
import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "event_page_contents")
class EventPageContentsJpaEntity(

    @Column(name = "contents_type", nullable = false)
    @Enumerated(EnumType.STRING)
    val contentsType: EventPageContentsType,

    @Column(name = "order_no", nullable = false)
    val orderNo: Int,

    @Column(name = "slide_order_no", nullable = false)
    val slideOrderNo: Int,

    @Column(name = "image_url", nullable = false)
    val imageUrl: String,

    @Column(name = "link_url", nullable = false)
    val linkUrl: String,

    @Column(name = "text_content")
    val textContent: String? = null,

    @Column(name = "is_add_post", nullable = false)
    val isAddPost: Boolean,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt)
