package com.project.minehair.global.response

data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val pagination: PaginationInfo? = null,
    val error: ErrorInfo? = null
) {
    data class ErrorInfo(
        val code: String,
        val message: String,
        val details: Any? = null
    )

    data class PaginationInfo(
        val currentPage: Int,        // 현재 페이지 번호 (1부터 시작)
        val totalPages: Int,         // 전체 페이지 수
        val totalElements: Long,     // 전체 데이터 개수
        val size: Int,              // 페이지당 데이터 개수
        val hasNext: Boolean,        // 다음 페이지 존재 여부
        val hasPrevious: Boolean,    // 이전 페이지 존재 여부
        val isFirst: Boolean,        // 첫 번째 페이지 여부
        val isLast: Boolean         // 마지막 페이지 여부
    )

    companion object {
        // 성공 응답 생성
        fun <T> success(
            data: T,
            message: String = "Success"
        ): BaseResponse<T> {
            return BaseResponse(
                success = true,
                message = message,
                pagination = null,
                data = data,
                error = null
            )
        }

        // 성공 응답 생성
        fun <T> success(
            data: T,
            pagination: PaginationInfo,
            message: String = "Success"
        ): BaseResponse<T> {
            return BaseResponse(
                success = true,
                message = message,
                pagination = pagination,
                data = data,
                error = null
            )
        }

        // 에러 응답 생성
        fun error(code: String, message: String, details: Any? = null): BaseResponse<Nothing?> {
            return BaseResponse(
                success = false,
                message = "Request failed",
                data = null,
                error = ErrorInfo(code, message, details)
            )
        }

        // 데이터 없는 성공 응답
        fun ok(message: String = "Success"): BaseResponse<Nothing?> {
            return BaseResponse(
                success = true,
                message = message,
                data = null,
                error = null
            )
        }

        // PaginationInfo 생성 헬퍼 함수
        fun createPaginationInfo(
            currentPage: Int,
            totalElements: Long,
            size: Int
        ): PaginationInfo {
            val totalPages = if (totalElements == 0L) 0 else ((totalElements - 1) / size + 1).toInt()

            return PaginationInfo(
                currentPage = currentPage,
                totalPages = totalPages,
                totalElements = totalElements,
                size = size,
                hasNext = currentPage < totalPages,
                hasPrevious = currentPage > 1,
                isFirst = currentPage == 1,
                isLast = currentPage == totalPages || totalPages == 0
            )
        }
    }
}