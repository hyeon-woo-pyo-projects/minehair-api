package com.project.minehair.domain.image.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class ImageUploadResponse(

    @Schema(name = "imageUrl", description = "이미지 접근 URL")
    val imageUrl: String,           // 접근 가능한 전체 URL
    @Schema(name = "fileName", description = "저장된 파일명")
    val fileName: String,           // 저장된 파일명
    @Schema(name = "originalFileName", description = "원본 파일명")
    val originalFileName: String,   // 원본 파일명
    @Schema(name = "filePath", description = "서버 내 상대 경로")
    val filePath: String,           // 서버 내 상대 경로
    @Schema(name = "fileSize", description = "파일 크기 (바이트 단위)")
    val fileSize: Long              // 파일 크기

)
