package com.project.minehair.domain.contents.adapter.`in`.web

import com.project.minehair.domain.contents.adapter.`in`.web.dto.CreateSnsPlatformRequest
import com.project.minehair.domain.contents.adapter.`in`.web.dto.SnsPlatformResponse
import com.project.minehair.domain.contents.adapter.`in`.web.dto.UpdateSnsPlatformRequest
import com.project.minehair.domain.contents.application.port.`in`.SnsPlatformUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "sns 플렛폼 API", description = "sns 플렛폼 API")
@RestController
@RequestMapping("/api/sns/platform")
class SnsPlatformInboundWebAdapter(
    private val snsPlatformUseCase: SnsPlatformUseCase
) {

    @Operation(summary = "리스트 조회", description = "리스트 조회")
    @GetMapping
    fun getSnsPlatformList(): BaseResponse<List<SnsPlatformResponse>> {
        return BaseResponse.success(snsPlatformUseCase.getSnsPlatformList())
    }

    @Operation(summary = "상세 조회", description = "상세 조회")
    @GetMapping("/details/{id}")
    fun getSnsPlatformDetails(@PathVariable id: Long): BaseResponse<SnsPlatformResponse> {
        return BaseResponse.success(snsPlatformUseCase.getSnsPlatformDetails(id))
    }

    @Operation(summary = "생성", description = "생성")
    @PostMapping
    fun createSnsPlatform(@Valid @RequestBody request: CreateSnsPlatformRequest): BaseResponse<SnsPlatformResponse> {
        return BaseResponse.success(snsPlatformUseCase.createSnsPlatform(request))
    }

    @Operation(summary = "수정", description = "수정")
    @PatchMapping("/{id}")
    fun updateSnsPlatform(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateSnsPlatformRequest
    ): BaseResponse<SnsPlatformResponse> {
        return BaseResponse.success(snsPlatformUseCase.updateSnsPlatform(id, request))
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/{id}")
    fun deleteSnsPlatform(@PathVariable id: Long): BaseResponse<SnsPlatformResponse> {
        return BaseResponse.success(snsPlatformUseCase.deleteSnsPlatform(id))
    }

}