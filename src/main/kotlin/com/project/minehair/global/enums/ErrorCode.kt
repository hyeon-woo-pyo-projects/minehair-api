package com.project.minehair.global.enums

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val code: String,
    val httpStatus: HttpStatus,
    val defaultMessage: String
) {
    // 4xx Client Errors
    INVALID_REQUEST("INVALID_REQUEST", HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_PARAMETER("INVALID_PARAMETER", HttpStatus.BAD_REQUEST, "잘못된 파라미터입니다."),
    MISSING_REQUIRED_FIELD("MISSING_REQUIRED_FIELD", HttpStatus.BAD_REQUEST, "필수 필드가 누락되었습니다."),

    INVALID_CREDENTIALS("INVALID_CREDENTIALS", HttpStatus.UNAUTHORIZED, "잘못된 인증 정보 입니다."),
    AUTHENTICATION_FAILED("AUTHENTICATION_FAILED", HttpStatus.UNAUTHORIZED, "인증에 실패했습니다."),

    UNAUTHORIZED("UNAUTHORIZED", HttpStatus.UNAUTHORIZED, "인증이 필요합니다."),
    INVALID_TOKEN("INVALID_TOKEN", HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다."),
    TOKEN_EXPIRED("TOKEN_EXPIRED", HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다."),

    FORBIDDEN("FORBIDDEN", HttpStatus.FORBIDDEN, "접근 권한이 없습니다."),
    ACCESS_DENIED("ACCESS_DENIED", HttpStatus.FORBIDDEN, "해당 리소스에 대한 접근이 거부되었습니다."),

    RESOURCE_NOT_FOUND("RESOURCE_NOT_FOUND", HttpStatus.NOT_FOUND, "요청한 리소스를 찾을 수 없습니다."),
    USER_NOT_FOUND("USER_NOT_FOUND", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),

    NOT_FOUND("NOT_FOUND", HttpStatus.NOT_FOUND, "데이터를 찾을 수 없습니다."),

    CONFLICT("CONFLICT", HttpStatus.CONFLICT, "리소스 충돌이 발생했습니다."),
    DUPLICATE_EMAIL("DUPLICATE_EMAIL", HttpStatus.CONFLICT, "이미 존재하는 이메일입니다."),
    DUPLICATE_PHONE("DUPLICATE_PHONE", HttpStatus.CONFLICT, "이미 존재하는 전화번호입니다."),
    DUPLICATE_USERID("DUPLICATE_USER_ID", HttpStatus.CONFLICT, "이미 존재하는 사용자명입니다."),

    // Business Rule Violations
    BUSINESS_RULE_VIOLATION("BUSINESS_RULE_VIOLATION", HttpStatus.BAD_REQUEST, "비즈니스 규칙에 위배됩니다."),
    AGE_RESTRICTION("AGE_RESTRICTION", HttpStatus.BAD_REQUEST, "연령 제한에 걸렸습니다."),
    INSUFFICIENT_BALANCE("INSUFFICIENT_BALANCE", HttpStatus.BAD_REQUEST, "잔액이 부족합니다."),
    INVALID_STATUS_TRANSITION("INVALID_STATUS_TRANSITION", HttpStatus.BAD_REQUEST, "유효하지 않은 상태 변경입니다."),

    // 5xx Server Errors
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류가 발생했습니다."),
    DATABASE_ERROR("DATABASE_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "데이터베이스 오류가 발생했습니다."),
    EXTERNAL_API_ERROR("EXTERNAL_API_ERROR", HttpStatus.INTERNAL_SERVER_ERROR, "외부 API 호출에 실패했습니다."),



}