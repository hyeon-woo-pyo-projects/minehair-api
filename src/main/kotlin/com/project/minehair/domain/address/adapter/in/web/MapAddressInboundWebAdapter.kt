package com.project.minehair.domain.address.adapter.`in`.web

import com.project.minehair.domain.address.adapter.`in`.web.dto.CreateMapAddressRequest
import com.project.minehair.domain.address.adapter.`in`.web.dto.MapAddressResponse
import com.project.minehair.domain.address.adapter.`in`.web.dto.UpdateMapAddressRequest
import com.project.minehair.domain.address.application.port.`in`.MapAddressUseCase
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "지도 주소 API", description = "지도 주소 API")
@RestController
@RequestMapping("/api/map-address")
class MapAddressInboundWebAdapter(
    private val mapAddressUseCase: MapAddressUseCase
) {

    @Operation(summary = "지도 주소 조회", description = "지도 주소 조회")
    @GetMapping
    fun getMapAddressList(): BaseResponse<List<MapAddressResponse>> {
        return BaseResponse.success(mapAddressUseCase.getMapAddressList())
    }

    @Operation(summary = "지도 주소 상세조회", description = "지도 주소 상세조회")
    @GetMapping("/details/{id}")
    fun getMapAddressDetails(@PathVariable id: Long): BaseResponse<MapAddressResponse> {
        return BaseResponse.success(mapAddressUseCase.getMapAddressDetails(id))
    }

    @Operation(summary = "지도 주소 작성", description = "지도 주소 작성")
    @PostMapping
    fun createMapAddress(@Valid @RequestBody request: CreateMapAddressRequest): BaseResponse<MapAddressResponse> {
        return BaseResponse.success(mapAddressUseCase.createMapAddress(request))
    }

    @Operation(summary = "지도 주소 수정", description = "지도 주소 수정")
    @PatchMapping("/{id}")
    fun updateMapAddress(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateMapAddressRequest
    ): BaseResponse<MapAddressResponse> {
        return BaseResponse.success(mapAddressUseCase.updateMapAddress(id, request))
    }

    @Operation(summary = "지도 주소 삭제", description = "지도 주소 삭제")
    @DeleteMapping("/{id}")
    fun deleteMapAddress(@PathVariable id: Long): BaseResponse<MapAddressResponse> {
        return BaseResponse.success(mapAddressUseCase.deleteMapAddress(id))
    }

}