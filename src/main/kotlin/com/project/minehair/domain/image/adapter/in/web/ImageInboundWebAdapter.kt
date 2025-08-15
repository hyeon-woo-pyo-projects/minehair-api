package com.project.minehair.domain.image.adapter.`in`.web

import com.project.minehair.domain.image.adapter.`in`.web.dto.ImageUploadResponse
import com.project.minehair.domain.image.application.port.`in`.ImageUseCase
import com.project.minehair.domain.image.domain.ImagePathType
import com.project.minehair.global.response.BaseResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@Tag(name = "이미지 API", description = "이미지 API")
@RestController
@RequestMapping("/api/image")
class ImageInboundWebAdapter(
    private val imageUseCase: ImageUseCase
) {

    /**
     * 이미지 업로드
     */
    @Operation(summary = "이미지 업로드", description = "이미지 파일을 업로드하고 URL을 반환합니다")
    @PostMapping(
        value = ["/upload/{imagePath}"],
        consumes = [MediaType.MULTIPART_FORM_DATA_VALUE]
    )
    fun uploadImage(
        @PathVariable("imagePath") imagePathType: ImagePathType,
        @RequestParam("imageFile") imageFile: MultipartFile
    ): BaseResponse<ImageUploadResponse> {
        return BaseResponse.success(imageUseCase.uploadImage(imagePathType, imageFile))
    }
}