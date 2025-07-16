package com.project.minehair.domain.role.adapter.`in`.web

import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "역할 API", description = "역할 API")
@RestController
@RequestMapping("/api/roles")
class RoleController {

    @GetMapping
    fun getAllRoles(){
    }

    @GetMapping("/{id}")
    fun getRole(@PathVariable id: Long) {
    }

}