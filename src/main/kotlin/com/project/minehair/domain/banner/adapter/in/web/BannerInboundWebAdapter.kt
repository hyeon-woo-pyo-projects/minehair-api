package com.project.minehair.domain.banner.adapter.`in`.web

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.CreateBannerRequest
import com.project.minehair.domain.banner.adapter.`in`.web.dto.UpdateBannerRequest
import com.project.minehair.domain.banner.application.port.`in`.BannerUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "배너 API", description = "배너 API")
@RestController
@RequestMapping("/api/banner")
class BannerInboundWebAdapter(
    private val bannerUseCase: BannerUseCase
) {

    @Operation(summary = "배너 리스트 조회", description = "배너 리스트 조회")
    @GetMapping
    fun getBannersList(): BaseResponse<List<BannerResponse>> {
        val responses = bannerUseCase.getBannersList()
        return BaseResponse.success(responses)
    }

    @Operation(summary = "배너 상세 조회", description = "배너 상세 조회")
    @GetMapping("/details/{id}")
    fun getBannersList(
        @PathVariable id: Long
    ): BaseResponse<BannerResponse> {
        return BaseResponse.success(bannerUseCase.getBannersDetails(id))
    }

    @Operation(summary = "배너 생성", description = "배너를 생성합니다.")
    @PostMapping()
    fun createBanner(
        @RequestBody request: CreateBannerRequest
    ): BaseResponse<BannerResponse> {
        return BaseResponse.success(bannerUseCase.createBanner(request))
    }

    @Operation(summary = "배너 수정", description = "배너 수정")
    @PatchMapping("/{id}")
    fun updateBanner(
        @PathVariable id: Long,
        @RequestBody request: UpdateBannerRequest
    ): BaseResponse<BannerResponse> {
        return BaseResponse.success(bannerUseCase.updateBanner(id, request))
    }

    @Operation(summary = "배너 삭제", description = "배너를 삭제합니다.")
    @DeleteMapping("/{id}")
    fun deleteBanner(
        @PathVariable id: Long,
    ): BaseResponse<BannerResponse> {
        return BaseResponse.success(bannerUseCase.deleteBanner(id))
    }

}