package com.project.minehair.global.security

import com.project.minehair.domain.auth.application.port.out.AuthDomainPort
import com.project.minehair.global.enums.ErrorCode
import com.project.minehair.global.exception.BusinessException
import org.springframework.security.core.authority.SimpleGrantedAuthority
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
        authorities.add(SimpleGrantedAuthority(role.code)) // 예: ROLE_USER, ROLE_ADMIN

        return CustomUserDetails(
            id = user.id,
            userId = user.userId,
            password = user.password,
            roleId = user.roleId,
            authorities = authorities
        )
    }
}