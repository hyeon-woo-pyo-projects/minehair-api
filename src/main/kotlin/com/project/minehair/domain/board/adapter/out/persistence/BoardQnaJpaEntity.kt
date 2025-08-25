package com.project.minehair.domain.board.adapter.out.persistence

import com.project.minehair.global.entity.BaseJpaEntity
import com.project.minehair.global.enums.Status
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "board_qna")
class BoardQnaJpaEntity(

    @Column(name = "title")
    val title: String,

    @Column(name = "content")
    val content: String,

    @Column(name = "author")
    val author: String,

    @Column(name = "view_count")
    val viewCount: Int,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long? = 0L,
    override val updatedAt: LocalDateTime? = null

) : BaseJpaEntity(id, status, createdId, createdAt, updatedId, updatedAt) {
}