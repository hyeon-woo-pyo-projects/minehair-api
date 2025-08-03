package com.project.minehair.global.utils

import org.springframework.beans.factory.annotation.Value
import org.springframework.security.oauth2.jwt.*
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.temporal.ChronoUnit

@Component
class JwtUtil(
    private val jwtEncoder: JwtEncoder,
    private val jwtDecoder: JwtDecoder,
    @Value("\${jwt.access-token-expiration}") private val accessTokenExpiration: Long,
    @Value("\${jwt.refresh-token-expiration}") private val refreshTokenExpiration: Long
) {

    /**
     * Access Token 생성
     */
    fun generateAccessToken(userId: String, roles: List<String>): String {
        val now = Instant.now()
        val claims = JwtClaimsSet.builder()
            .issuer("minehair")
            .issuedAt(now)
            .expiresAt(now.plus(accessTokenExpiration, ChronoUnit.MINUTES))
            .subject(userId)
            .claim("type", "access")
            .claim("roles", roles) // roles 추가
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }

    /**
     * Refresh Token 생성
     */
    fun generateRefreshToken(userId: String): String {
        val now = Instant.now()

        val claims = JwtClaimsSet.builder()
            .issuer("minehair")
            .issuedAt(now)
            .expiresAt(now.plus(refreshTokenExpiration, ChronoUnit.MINUTES))
            .subject(userId)
            .claim("type", "refresh")
            .build()

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).tokenValue
    }

    /**
     * 토큰에서 사용자 ID 추출
     */
    fun getUserIdFromToken(token: String): String {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.subject
        } catch (e: JwtException) {
            throw IllegalArgumentException("Invalid JWT token", e)
        }
    }

    /**
     * 토큰 유효성 검증
     */
    fun validateToken(token: String): Boolean {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.expiresAt?.isAfter(Instant.now()) == true
        } catch (e: JwtException) {
            false
        }
    }

    /**
     * 토큰 타입 확인 (access/refresh)
     */
    fun getTokenType(token: String): String? {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.getClaim("type")
        } catch (e: JwtException) {
            null
        }
    }

    /**
     * 토큰 만료 시간 조회 (Instant 반환)
     */
    fun getExpirationFromToken(token: String): Instant? {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.expiresAt
        } catch (e: JwtException) {
            null
        }
    }

    /**
     * 토큰 발급 시간 조회 (Instant 반환)
     */
    fun getIssuedAtFromToken(token: String): Instant? {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.issuedAt
        } catch (e: JwtException) {
            null
        }
    }

    // ===== ThreadLocal Context에서 사용할 Long 타입 메서드들 추가 =====

    /**
     * 토큰 만료 시간을 Long (epochMilli)으로 반환 - ThreadLocal Context용
     */
    fun getExpirationFromTokenAsLong(token: String): Long {
        return getExpirationFromToken(token)?.toEpochMilli() ?: 0L
    }

    /**
     * 토큰 발급 시간을 Long (epochMilli)으로 반환 - ThreadLocal Context용
     */
    fun getIssuedAtFromTokenAsLong(token: String): Long {
        return getIssuedAtFromToken(token)?.toEpochMilli() ?: 0L
    }

    /**
     * 토큰 만료 시간을 LocalDateTime으로 반환
     */
    fun getExpirationAsLocalDateTime(token: String): LocalDateTime? {
        return getExpirationFromToken(token)?.let { instant ->
            LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        }
    }

    /**
     * 토큰 발급 시간을 LocalDateTime으로 반환
     */
    fun getIssuedAtAsLocalDateTime(token: String): LocalDateTime? {
        return getIssuedAtFromToken(token)?.let { instant ->
            LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        }
    }

    /**
     * 토큰이 곧 만료되는지 확인 (기본 5분)
     */
    fun isTokenExpiringSoon(token: String, thresholdMinutes: Int = 5): Boolean {
        val remainingSeconds = getRemainingValidityInSeconds(token)
        return remainingSeconds <= (thresholdMinutes * 60)
    }

    /**
     * 토큰의 상세 정보를 반환하는 헬퍼 메서드
     */
    fun getTokenDetails(token: String): TokenDetails? {
        return try {
            val jwt = jwtDecoder.decode(token)
            TokenDetails(
                userId = jwt.subject,
                issuedAt = jwt.issuedAt?.toEpochMilli() ?: 0L,
                expiresAt = jwt.expiresAt?.toEpochMilli() ?: 0L,
                remainingSeconds = getRemainingValidityInSeconds(token),
                isExpiringSoon = isTokenExpiringSoon(token),
                tokenType = jwt.getClaim("type") ?: "unknown",
                roles = jwt.getClaimAsStringList("roles") ?: emptyList()
            )
        } catch (e: JwtException) {
            null
        }
    }

    /**
     * 토큰의 모든 클레임 조회
     */
    fun getAllClaimsFromToken(token: String): Map<String, Any> {
        return try {
            val jwt = jwtDecoder.decode(token)
            jwt.claims
        } catch (e: JwtException) {
            emptyMap()
        }
    }

    /**
     * 토큰에서 roles 추출
     */
    fun getRolesFromToken(token: String): List<String> {
        val jwt = jwtDecoder.decode(token)
        return jwt.getClaimAsStringList("roles") ?: emptyList()
    }

    /**
     * 토큰이 만료되었는지 확인
     */
    fun isTokenExpired(token: String): Boolean {
        return try {
            val expiration = getExpirationFromToken(token)
            expiration?.isBefore(Instant.now()) ?: true
        } catch (e: Exception) {
            true
        }
    }

    /**
     * Access Token인지 확인
     */
    fun isAccessToken(token: String): Boolean {
        return getTokenType(token) == "access"
    }

    /**
     * Refresh Token인지 확인
     */
    fun isRefreshToken(token: String): Boolean {
        return getTokenType(token) == "refresh"
    }

    /**
     * 토큰의 남은 유효시간(초) 계산
     */
    fun getRemainingValidityInSeconds(token: String): Long {
        return try {
            val expiration = getExpirationFromToken(token)
            if (expiration != null) {
                val now = Instant.now()
                if (expiration.isAfter(now)) {
                    expiration.epochSecond - now.epochSecond
                } else {
                    0L
                }
            } else {
                0L
            }
        } catch (e: Exception) {
            0L
        }
    }

    fun getAccessTokenExpiration(): Long {
        return accessTokenExpiration
    }

    fun getRefreshTokenExpiration(): Long {
        return refreshTokenExpiration
    }
}

// 토큰 상세 정보를 담는 데이터 클래스
data class TokenDetails(
    val userId: String,
    val issuedAt: Long,
    val expiresAt: Long,
    val remainingSeconds: Long,
    val isExpiringSoon: Boolean,
    val tokenType: String,
    val roles: List<String>
)