package com.project.minehair.global.exception


import com.project.minehair.global.response.BaseResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class GlobalExceptionHandler {

    /**
     * BusinessException 처리
     * ErrorCode enum에서 HTTP 상태 코드를 자동으로 가져옵니다.
     */
    @ExceptionHandler(BusinessException::class)
    fun handleBusinessException(e: BusinessException): ResponseEntity<BaseResponse<Nothing?>> {
        val response = BaseResponse.error(
            code = e.errorCode.code,
            message = e.message,
            details = e.details
        )

        return ResponseEntity.status(e.errorCode.httpStatus).body(response)
    }

    /**
     * DTO @Valid 검증 실패 (RequestBody)
     */
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Nothing?>> {
        val errorDetails = ex.bindingResult.fieldErrors.associate { fieldError ->
            fieldError.field to (fieldError.defaultMessage ?: "잘못된 입력입니다.")
        }

        val response = BaseResponse.error(
            code = "VALIDATION_FAILED",
            message = "요청 값이 유효하지 않습니다.",
            details = errorDetails
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    /**
     * 파라미터 제약조건 실패 (e.g. @RequestParam, @PathVariable)
     */
    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolation(ex: ConstraintViolationException): ResponseEntity<BaseResponse<Nothing?>> {
        val errorDetails = ex.constraintViolations.associate { violation ->
            violation.propertyPath.toString() to violation.message
        }

        val response = BaseResponse.error(
            code = "CONSTRAINT_VIOLATION",
            message = "요청 파라미터가 유효하지 않습니다.",
            details = errorDetails
        )

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

    /**
     * 일반적인 Exception 처리 (마지막 fallback)
     */
    @ExceptionHandler(Exception::class)
    fun handleGenericException(ex: Exception): ResponseEntity<BaseResponse<Nothing?>> {
        val response = BaseResponse.error(
            code = "INTERNAL_SERVER_ERROR",
            message = "서버 내부 오류가 발생했습니다.",
            details = ex.localizedMessage
        )

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response)
    }
}