package com.project.minehair.domain.banner.adapter.`in`.web

import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerResponse
import com.project.minehair.domain.banner.adapter.`in`.web.dto.BannerUpdateRequest
import com.project.minehair.domain.banner.application.port.`in`.BannerUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "배너 API", description = "배너 API")
@RestController
@RequestMapping("/api/banner")
class BannerInboundWebAdapter(
    private val bannerUseCase: BannerUseCase
) {

//    /**
//     * 게시상태의 배너 조회
//     */
//    @Operation(summary = "게시상태의 배너 조회", description = "게시상태의 배너 조회")
//    @GetMapping("/post")
//    fun getPostBanner(): BaseResponse<BannerResponse> {
//        return BaseResponse.success(bannerUseCase.getPostBanner())
//    }

//    /**
//     * 배너 생성
//     */
//    @Operation(summary = "배너 생성", description = "배너 생성")
//    @PostMapping
//    fun createBanner(@Valid @RequestBody request: BannerCreateRequest): BaseResponse<Nothing?> {
//        bannerUseCase.createBanner(request)
//        return BaseResponse.ok()
//    }

    /**
     * 배너 리스트 조회
     */
    @Operation(summary = "배너 리스트 조회", description = "배너 리스트 조회")
    @GetMapping
    fun getBannersList(): BaseResponse<List<BannerResponse>> {
        val responses = bannerUseCase.getBannersList()
        return BaseResponse.success(responses)
    }

//    /**
//     * 배너 상세 조회
//     */
//    @Operation(summary = "배너 상세 조회", description = "배너 상세 조회")
//    @GetMapping("/{id}")
//    fun getBannerById(id: Long): BaseResponse<BannerResponse> {
//        val response = bannerUseCase.getBannerById(id)
//        return BaseResponse.success(response)
//    }

    /**
     * 배너 수정
     */
    @Operation(summary = "배너 수정", description = "배너 수정")
    @PatchMapping("/{id}")
    fun updateBanner(
        @PathVariable id: Long,
        @RequestBody request: BannerUpdateRequest
    ): BaseResponse<Nothing?> {
        bannerUseCase.updateBanner(id, request)
        return BaseResponse.ok()
    }

//    /**
//     * 배너 삭제
//     */
//    @Operation(summary = "배너 삭제", description = "배너 삭제")
//    @DeleteMapping("/{id}")
//    fun deleteBanner(id: Long): BaseResponse<Nothing?> {
//        bannerUseCase.deleteBanner(id)
//        return BaseResponse.ok()
//    }

}