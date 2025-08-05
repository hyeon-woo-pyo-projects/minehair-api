package com.project.minehair.global.domain.inter

data class InterDomainMenuInfo(
    val id: Long,
    val parentId: Long?,
    val name: String,
    val path: String,
    val imageUrl: String?,
    val orderNo: Int,
    val visible: Boolean
)

