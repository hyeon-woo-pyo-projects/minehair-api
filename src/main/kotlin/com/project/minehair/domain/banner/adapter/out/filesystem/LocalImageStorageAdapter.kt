package com.project.minehair.domain.banner.adapter.out.filesystem

import com.project.minehair.domain.image.application.port.out.ImageStoragePort
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardCopyOption

@Component
class LocalImageStorageAdapter(
    private val uploadBasePath: String = "/app/uploads",
    private val baseUrl: String = "http://localhost:8080"
) : ImageStoragePort {

    override fun saveImage(file: MultipartFile, fullPath: String): String {
        try {
            // 1. 저장할 전체 경로 생성 (fullPath는 이미 타입별 경로 + 파일명 포함)
            val savePath = Paths.get(uploadBasePath + fullPath)

            // 2. 디렉토리가 없으면 생성
            createDirectoriesIfNotExists(savePath.parent)

            // 3. 파일 저장
            Files.copy(file.inputStream, savePath, StandardCopyOption.REPLACE_EXISTING)

            // 4. 접근 가능한 URL 반환
            return "$baseUrl/uploads$fullPath"

        } catch (e: Exception) {
            throw RuntimeException("파일 저장 중 오류가 발생했습니다: ${e.message}", e)
        }
    }

    private fun createDirectoriesIfNotExists(dirPath: Path) {
        if (!Files.exists(dirPath)) {
            Files.createDirectories(dirPath)
        }
    }
}