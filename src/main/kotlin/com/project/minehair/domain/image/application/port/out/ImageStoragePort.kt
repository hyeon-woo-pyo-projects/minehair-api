package com.project.minehair.domain.image.application.port.out

import org.springframework.web.multipart.MultipartFile

interface ImageStoragePort {

    /**
     * 이미지 파일 저장
     */
    fun saveImage(file: MultipartFile, fullPath: String): String

}