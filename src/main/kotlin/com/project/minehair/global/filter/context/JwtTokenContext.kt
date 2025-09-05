package com.project.minehair.global.filter.context

import com.project.minehair.global.filter.dto.JwtTokenInfo
import org.springframework.stereotype.Component

@Component
class JwtTokenContext {

    companion object {
        private val tokenContext = ThreadLocal<JwtTokenInfo>()

        fun setToken(tokenInfo: JwtTokenInfo) {
            tokenContext.set(tokenInfo)
        }

        fun getToken(): JwtTokenInfo? {
            return tokenContext.get()
        }

        fun getId(): Long {
            return tokenContext.get().id
        }

        fun getUserId(): String? {
            return tokenContext.get()?.userId
        }

        fun getAuthorities(): List<String> {
            return tokenContext.get()?.authorities ?: listOf("ROLE_GUEST")
        }

        fun clear() {
            tokenContext.remove()
        }

        fun hasToken(): Boolean {
            return tokenContext.get() != null
        }
    }
}