package com.project.minehair.domain.board.adapter.`in`.web.dto

import com.project.minehair.global.request.PageRequest

data class BoardQnaPageRequest(
    override val page: Int = 1,
    override val size: Int = 10,
) : PageRequest(page, size)
