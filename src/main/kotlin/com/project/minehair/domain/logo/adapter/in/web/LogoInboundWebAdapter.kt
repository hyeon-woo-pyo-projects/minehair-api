package com.project.minehair.domain.logo.adapter.`in`.web

import com.project.minehair.domain.logo.adapter.`in`.web.dto.CreateLogoRequest
import com.project.minehair.domain.logo.adapter.`in`.web.dto.LogoResponse
import com.project.minehair.domain.logo.adapter.`in`.web.dto.UpdateLogoRequest
import com.project.minehair.domain.logo.application.port.`in`.LogoUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "로고 API", description = "로고 API")
@RestController
@RequestMapping("/api/logo")
class LogoInboundWebAdapter(
    private val logoUseCase: LogoUseCase
) {

    @Operation(summary = "리스트 조회", description = "리스트 조회")
    @GetMapping
    fun getLogoList(): BaseResponse<List<LogoResponse>> {
        return BaseResponse.success(logoUseCase.getLogoList())
    }

    @Operation(summary = "상세 조회", description = "상세 조회")
    @GetMapping("/details/{id}")
    fun getLogoDetails(@PathVariable id: Long): BaseResponse<LogoResponse> {
        return BaseResponse.success(logoUseCase.getLogoDetails(id))
    }

    @Operation(summary = "생성", description = "생성")
    @PostMapping
    fun createLogo(@Valid @RequestBody request: CreateLogoRequest): BaseResponse<LogoResponse> {
        return BaseResponse.success(logoUseCase.createLogo(request))
    }

    @Operation(summary = "수정", description = "수정")
    @PatchMapping("/{id}")
    fun updateLogo(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateLogoRequest
    ): BaseResponse<LogoResponse> {
        return BaseResponse.success(logoUseCase.updateLogo(id, request))
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/{id}")
    fun deleteLogo(@PathVariable id: Long): BaseResponse<LogoResponse> {
        return BaseResponse.success(logoUseCase.deleteLogo(id))
    }

}