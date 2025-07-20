package com.project.minehair.global.response

data class BaseResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null,
    val error: ErrorInfo? = null
) {
    data class ErrorInfo(
        val code: String,
        val message: String,
        val details: Any? = null
    )

    companion object {
        // 성공 응답 생성
        fun <T> success(data: T, message: String = "Success"): BaseResponse<T> {
            return BaseResponse(
                success = true,
                message = message,
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
    }
}