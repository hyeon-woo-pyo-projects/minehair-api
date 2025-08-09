package com.project.minehair.domain.image.application.port.`in`

import org.springframework.web.multipart.MultipartFile

interface ImageUseCase {

    /**
     * 이미지 업로드
     */
    fun uploadImage(imagePath: String, imageFile: MultipartFile): String
}