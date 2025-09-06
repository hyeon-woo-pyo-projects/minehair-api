package com.project.minehair.domain.banner.domain

import com.project.minehair.domain.banner.adapter.`in`.web.dto.UpdateBannerRequest
import com.project.minehair.global.domain.BaseDomain
import com.project.minehair.global.enums.Status
import java.time.LocalDateTime

data class Banner(

    val bannerType: BannerType,
    val content: String?,
    val color: String?,
    val textColor: String?,
    val link: String,
    val imageUrl: String,
    val isPost: Boolean,

    override val id: Long? = null,
    override val status: Status = Status.active,
    override val createdId: Long = 0L,
    override val createdAt: LocalDateTime = LocalDateTime.now(),
    override val updatedId: Long = 0L,
    override val updatedAt: LocalDateTime? = null
) : BaseDomain(id, status, createdId, createdAt, updatedId, updatedAt) {

    // 수정 메서드
    fun updateFrom(
        updateRequest: UpdateBannerRequest
    ): Banner {
        return copy(
            bannerType = updateRequest.bannerType,
            content = updateRequest.content,
            color = updateRequest.color,
            textColor = updateRequest.textColor,
            link = updateRequest.link,
            imageUrl = updateRequest.imageUrl,
            isPost = updateRequest.isPost,
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
