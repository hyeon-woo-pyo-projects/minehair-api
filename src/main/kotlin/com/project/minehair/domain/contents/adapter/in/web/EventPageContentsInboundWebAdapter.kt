package com.project.minehair.domain.contents.adapter.`in`.web

import com.project.minehair.domain.contents.adapter.`in`.web.dto.*
import com.project.minehair.domain.contents.application.port.`in`.EventPageContentsUseCase
import com.project.minehair.domain.contents.domain.EventPageContentsType
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@Tag(name = "이벤트 페이지 컨텐츠 API", description = "이벤트 페이지 컨텐츠 API")
@RestController
@RequestMapping("/api/event/page/contents")
class EventPageContentsInboundWebAdapter(
    private val eventPageContentsUseCase: EventPageContentsUseCase
) {

    @Operation(summary = "리스트 조회", description = "리스트 조회")
    @GetMapping("/{contentsType}")
    fun getEventPageContentsList(@PathVariable contentsType: EventPageContentsType): BaseResponse<List<EventPageContentsResponse>> {
        return BaseResponse.success(eventPageContentsUseCase.getEventPageContentsList(contentsType))
    }

    @Operation(summary = "상세 조회", description = "상세 조회")
    @GetMapping("/details/{id}")
    fun getEventPageContentsDetails(@PathVariable id: Long): BaseResponse<EventPageContentsResponse> {
        return BaseResponse.success(eventPageContentsUseCase.getEventPageContentsDetails(id))
    }

    @Operation(summary = "생성", description = "생성")
    @PostMapping
    fun createEventPageContents(@Valid @RequestBody request: CreateEventPageContentsRequest): BaseResponse<EventPageContentsResponse> {
        return BaseResponse.success(eventPageContentsUseCase.createEventPageContents(request))
    }

    @Operation(summary = "수정", description = "수정")
    @PatchMapping("/{id}")
    fun updateEventPageContents(
        @PathVariable id: Long,
        @Valid @RequestBody request: UpdateEventPageContentsRequest
    ): BaseResponse<EventPageContentsResponse> {
        return BaseResponse.success(eventPageContentsUseCase.updateEventPageContents(id, request))
    }

    @Operation(summary = "삭제", description = "삭제")
    @DeleteMapping("/{id}")
    fun deleteEventPageContents(@PathVariable id: Long): BaseResponse<EventPageContentsResponse> {
        return BaseResponse.success(eventPageContentsUseCase.deleteEventPageContents(id))
    }

}