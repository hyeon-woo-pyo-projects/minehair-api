package com.project.minehair.domain.image.application.service

import com.project.minehair.domain.image.application.port.`in`.ImageUseCase
import com.project.minehair.domain.image.application.port.out.ImageStoragePort
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class ImageService(
    private val imageStoragePort: ImageStoragePort
): ImageUseCase {

    override fun uploadImage(imagePath: String, imageFile: MultipartFile): String {
        // 1. 파일 검증
        validateImageFile(imageFile)

        // 2. 파일 저장 경로 생성
        val fullPath = generateFullPath(imagePath, imageFile)

        // 3. 파일 저장 (아웃바운드 포트 호출)
        return imageStoragePort.saveImage(imageFile, fullPath)
    }

    private fun validateImageFile(file: MultipartFile) {
        if (file.isEmpty) {
            throw IllegalArgumentException("파일이 비어있습니다")
        }

        // 파일 크기 체크 (10MB)
        if (file.size > 10 * 1024 * 1024) {
            throw IllegalArgumentException("파일 크기가 10MB를 초과합니다")
        }

        // 이미지 파일 타입 체크
        val contentType = file.contentType
        if (contentType == null || !contentType.startsWith("image/")) {
            throw IllegalArgumentException("이미지 파일만 업로드 가능합니다")
        }
    }

    private fun generateFullPath(imagePath: String, file: MultipartFile): String {
        val timestamp = System.currentTimeMillis()
        val extension = file.originalFilename?.substringAfterLast('.') ?: "jpg"
        val fileName = "${timestamp}.${extension}"

        // imagePath가 '/'로 시작하지 않으면 추가
        val normalizedPath = if (imagePath.startsWith("/")) imagePath else "/$imagePath"

        return "$normalizedPath/$fileName"
    }

}