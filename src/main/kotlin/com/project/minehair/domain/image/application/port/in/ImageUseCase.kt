package com.project.minehair.domain.image.application.port.`in`

import com.project.minehair.domain.image.adapter.`in`.web.dto.ImageUploadResponse
import com.project.minehair.domain.image.domain.ImagePathType
import org.springframework.web.multipart.MultipartFile

interface ImageUseCase {

    /**
     * 이미지 업로드
     */
    fun uploadImage(imagePathType: ImagePathType, imageFile: MultipartFile): ImageUploadResponse
}