package com.project.minehair.global.utils

import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.*
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

@Component
class CryptoUtil(
    private val secretKey: String = "minehair"
) {

    companion object {
        private const val ALGORITHM = "AES"
        private const val TRANSFORMATION = "AES/GCM/NoPadding"
        private const val GCM_IV_LENGTH = 12
        private const val GCM_TAG_LENGTH = 16
    }

    private val secretKeySpec: SecretKeySpec by lazy {
        // 32바이트 키로 패딩 또는 자르기
        val keyBytes = secretKey.toByteArray(StandardCharsets.UTF_8)
        val paddedKey = ByteArray(32)
        System.arraycopy(keyBytes, 0, paddedKey, 0, minOf(keyBytes.size, 32))
        SecretKeySpec(paddedKey, ALGORITHM)
    }

    /**
     * 문자열을 AES-GCM으로 암호화
     * @param plainText 암호화할 평문
     * @return Base64로 인코딩된 암호화 문자열
     */
    fun encrypt(plainText: String): String {
        try {
            val cipher = Cipher.getInstance(TRANSFORMATION)

            // IV 생성
            val iv = ByteArray(GCM_IV_LENGTH)
            SecureRandom().nextBytes(iv)
            val gcmParameterSpec = GCMParameterSpec(GCM_TAG_LENGTH * 8, iv)

            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, gcmParameterSpec)
            val encryptedData = cipher.doFinal(plainText.toByteArray(StandardCharsets.UTF_8))

            // IV + 암호화된 데이터를 결합
            val encryptedWithIv = ByteArray(iv.size + encryptedData.size)
            System.arraycopy(iv, 0, encryptedWithIv, 0, iv.size)
            System.arraycopy(encryptedData, 0, encryptedWithIv, iv.size, encryptedData.size)

            return Base64.getEncoder().encodeToString(encryptedWithIv)
        } catch (e: Exception) {
            throw RuntimeException("암호화 중 오류가 발생했습니다.", e)
        }
    }

    /**
     * AES-GCM으로 암호화된 문자열을 복호화
     * @param encryptedText Base64로 인코딩된 암호화 문자열
     * @return 복호화된 평문
     */
    fun decrypt(encryptedText: String): String {
        try {
            val encryptedWithIv = Base64.getDecoder().decode(encryptedText)

            // IV 추출
            val iv = ByteArray(GCM_IV_LENGTH)
            System.arraycopy(encryptedWithIv, 0, iv, 0, iv.size)

            // 암호화된 데이터 추출
            val encryptedData = ByteArray(encryptedWithIv.size - GCM_IV_LENGTH)
            System.arraycopy(encryptedWithIv, GCM_IV_LENGTH, encryptedData, 0, encryptedData.size)

            val cipher = Cipher.getInstance(TRANSFORMATION)
            val gcmParameterSpec = GCMParameterSpec(GCM_TAG_LENGTH * 8, iv)
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, gcmParameterSpec)

            val decryptedData = cipher.doFinal(encryptedData)
            return String(decryptedData, StandardCharsets.UTF_8)
        } catch (e: Exception) {
            throw RuntimeException("복호화 중 오류가 발생했습니다.", e)
        }
    }

    /**
     * 비밀번호 해싱 (단방향)
     * @param password 해싱할 비밀번호
     * @return 해싱된 비밀번호
     */
    fun hashPassword(password: String): String {
        // BCrypt나 Argon2 사용을 권장하지만, 간단한 예시로 SHA-256 사용
        val bytes = password.toByteArray(StandardCharsets.UTF_8)
        val digest = java.security.MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(bytes)
        return Base64.getEncoder().encodeToString(hashedBytes)
    }

    /**
     * 랜덤 AES 키 생성
     * @return Base64로 인코딩된 AES 키
     */
    fun generateAESKey(): String {
        val keyGenerator = KeyGenerator.getInstance(ALGORITHM)
        keyGenerator.init(256)
        val secretKey = keyGenerator.generateKey()
        return Base64.getEncoder().encodeToString(secretKey.encoded)
    }

    /**
     * 검색을 위한 해시 생성 (고정 솔트 사용)
     * @param data 검색할 데이터
     * @return Base64로 인코딩된 해시
     */
    fun hashForSearch(data: String): String {
        val salt = "your-fixed-salt-for-search" // 고정 솔트
        val saltedData = salt + data
        val digest = MessageDigest.getInstance("SHA-256")
        return Base64.getEncoder().encodeToString(digest.digest(saltedData.toByteArray()))
    }
}