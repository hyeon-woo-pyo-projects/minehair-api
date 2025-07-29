package com.project.minehair.global.exception

import com.project.minehair.global.enums.ErrorCode

class BusinessException(
    val errorCode: ErrorCode,
    override val message: String = errorCode.defaultMessage,
    val details: Any? = null
) : RuntimeException(message) {

    // 하위 호환성을 위한 프로퍼티
    val code: String get() = errorCode.code

    companion object {
        // ErrorCode를 사용하는 편의 메서드들
        fun of(errorCode: ErrorCode, message: String? = null, details: Any? = null) =
            BusinessException(errorCode, message ?: errorCode.defaultMessage, details)

        // 자주 사용되는 에러들을 위한 편의 메서드들
        fun invalidRequest(message: String? = null, details: Any? = null) =
            of(ErrorCode.INVALID_REQUEST, message, details)

        fun notFound(message: String? = null, details: Any? = null) =
            of(ErrorCode.RESOURCE_NOT_FOUND, message, details)

        fun userNotFound(message: String? = null, details: Any? = null) =
            of(ErrorCode.USER_NOT_FOUND, message, details)

        fun unauthorized(message: String? = null, details: Any? = null) =
            of(ErrorCode.UNAUTHORIZED, message, details)

        fun forbidden(message: String? = null, details: Any? = null) =
            of(ErrorCode.FORBIDDEN, message, details)

        fun conflict(message: String? = null, details: Any? = null) =
            of(ErrorCode.CONFLICT, message, details)
    }
}