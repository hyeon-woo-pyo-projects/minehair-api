package com.project.minehair.domain.contents.adapter.out.persistence

import com.project.minehair.domain.contents.domain.ContentsType
import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "page_contents")
class PageContentsJpaEntity(

    @Column(name = "menu_id", nullable = false)
    val menuId: Long,

    @Column(name = "page_url", nullable = true)
    val pageUrl: String?,

    @Column(name = "contents_type", nullable = false)
    val contentsType: ContentsType,

    @Column(name = "contents_url", nullable = false)
    val contentsUrl: String,

    @Column(name = "order_no", nullable = false)
    val orderNo: Int,

    @Column(name = "video_back_ground_url", nullable = true)
    val videoBackGroundUrl: String?,

    @Column(name = "consulting_back_ground_url", nullable = true)
    val consultingBackGroundUrl: String?,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt)
