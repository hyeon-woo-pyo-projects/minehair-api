package com.project.minehair.global.request

import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min

open class PageRequest(
    @field:Min(value = 1, message = "페이지 번호는 1 이상이어야 합니다")
    open val page: Int = 1,

    @field:Min(value = 1, message = "페이지 크기는 1 이상이어야 합니다")
    @field:Max(value = 100, message = "페이지 크기는 100 이하여야 합니다")
    open val size: Int = 10
)