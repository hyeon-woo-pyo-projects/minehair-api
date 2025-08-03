package com.project.minehair.domain.role.adapter.`in`.web

import com.project.minehair.domain.role.adapter.`in`.web.dto.RoleResponse
import com.project.minehair.domain.role.application.port.`in`.RoleUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "역할 API", description = "역할 API")
@RestController
@RequestMapping("/api/roles")
class RoleController (
    private val roleUseCase: RoleUseCase
) {


    @GetMapping
    fun getAllRoles(): BaseResponse<List<RoleResponse>>{
        val roles = roleUseCase.getAllRoles()
        return BaseResponse.success(roles)
    }

//    @GetMapping("/{id}")
//    fun getRole(@PathVariable id: Long) {
//    }

}