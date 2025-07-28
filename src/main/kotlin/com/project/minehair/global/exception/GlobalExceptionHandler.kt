package com.project.minehair.global.exception


import com.project.minehair.global.response.BaseResponse
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class GlobalExceptionHandler {

    // DTO @Valid 검증 실패 (RequestBody)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: MethodArgumentNotValidException): BaseResponse<Nothing?> {
        val errorDetails = ex.bindingResult.fieldErrors.associate { fieldError ->
            fieldError.field to (fieldError.defaultMessage ?: "잘못된 입력입니다.")
        }

        return BaseResponse.error(
            code = "400",
            message = "요청 값이 유효하지 않습니다.",
            details = errorDetails
        )
    }

    // 파라미터 제약조건 실패 (e.g. @RequestParam, @PathVariable)
    @ExceptionHandler(ConstraintViolationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleConstraintViolation(ex: ConstraintViolationException): BaseResponse<Nothing?> {
        val errorDetails = ex.constraintViolations.associate { violation ->
            violation.propertyPath.toString() to violation.message
        }

        return BaseResponse.error(
            code = "400",
            message = "요청 파라미터가 유효하지 않습니다.",
            details = errorDetails
        )
    }

    // 그 외 예외 처리
    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleGenericException(ex: Exception): BaseResponse<Nothing?> {
        return BaseResponse.error(
            code = "500",
            message = "서버 내부 오류가 발생했습니다.",
            details = ex.localizedMessage
        )
    }
}