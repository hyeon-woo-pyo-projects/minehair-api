package com.project.minehair.domain.auth.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class LoginRequest(

    @Schema(description = "아이디", required = true, example = "system2")
    val userId: String,

    @Schema(description = "패스워드", required = true, example = "system")
    val password: String

)