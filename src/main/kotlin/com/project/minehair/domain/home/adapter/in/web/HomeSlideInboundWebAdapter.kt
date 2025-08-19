package com.project.minehair.domain.home.adapter.`in`.web

import com.project.minehair.domain.home.adapter.`in`.web.dto.CreateHomeSlideRequest
import com.project.minehair.domain.home.adapter.`in`.web.dto.HomeSlideResponse
import com.project.minehair.domain.home.application.port.`in`.HomeSlideUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "홈 슬라이드 API", description = "홈 슬라이드 API")
@RestController
@RequestMapping("/api/home/slide")
class HomeSlideInboundWebAdapter(
    private val homeSlideUseCase: HomeSlideUseCase
) {

    @Operation(summary = "홈 슬라이드 리스트 조회", description = "홈 슬라이드 리스트 조회")
    @GetMapping
    fun getHomeSlideList(): BaseResponse<List<HomeSlideResponse>> {
        return BaseResponse.success(homeSlideUseCase.getHomeSlideList())
    }

    @Operation(summary = "홈 슬라이드 생성", description = "홈 슬라이드 생성")
    @PostMapping
    fun createHomeSlide(@Valid @RequestBody request: CreateHomeSlideRequest): BaseResponse<HomeSlideResponse> {
        return BaseResponse.success(homeSlideUseCase.createHomeSlide(request))
    }

    @Operation(summary = "홈 슬라이드 삭제", description = "홈 슬라이드 삭제")
    @DeleteMapping("/{id}")
    fun deleteHomeSlide(@PathVariable id: Long): BaseResponse<HomeSlideResponse> {
        return BaseResponse.success(homeSlideUseCase.deleteHomeSlide(id))
    }

}