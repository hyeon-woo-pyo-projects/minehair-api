package com.project.minehair.domain.banner.domain

import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class Banner(
    val content: String,
    val color: String,
    val link: String,
    val isPost: Boolean,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    companion object {
        // 정적 팩토리 메서드로 생성
        fun of(
            content: String,
            color: String,
            link: String,
            isPost: Boolean = false
        ): Banner {
            return Banner(
                content = content,
                color = color,
                link = link,
                isPost = isPost,
                createdId = 1L,
                createdAt = LocalDateTime.now()
            )
        }
    }

    // 수정 메서드
    fun update(
        content: String,
        color: String,
        link: String,
        isPost: Boolean
    ): Banner {
        return copy(
            content = content,
            color = color,
            link = link,
            isPost = isPost,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }

    // 삭제 메서드 (소프트 삭제)
    fun delete(): Banner {
        return copy(
            status = Status.deleted,
            updatedId = updatedId,
            updatedAt = LocalDateTime.now()
        )
    }
}
