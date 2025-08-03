package com.project.minehair.global.security

import com.project.minehair.domain.auth.application.port.out.AuthDomainPort
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(
    private val authDomainPort: AuthDomainPort
) : UserDetailsService {

    override fun loadUserByUsername(userId: String): UserDetails {
        val user = authDomainPort.getUserByUserId(userId)
            ?: throw BusinessException(ErrorCode.USER_NOT_FOUND)

        val role = authDomainPort.getRoleById(user.roleId)
            ?: throw BusinessException(ErrorCode.NOT_FOUND, "role")

        val authorities = mutableListOf<SimpleGrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_${role.code}")) // 예: ROLE_USER, ROLE_ADMIN

        return User.builder()
            .username(user.userId)
            .password(user.password) // 암호화된 비밀번호
            .authorities(authorities) // 권한
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}