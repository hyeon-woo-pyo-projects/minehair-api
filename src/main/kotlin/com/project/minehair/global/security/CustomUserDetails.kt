package com.project.minehair.global.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val id: Long,
    private val userId: String,
    private val password: String,
    private val roleId: Long,
    private val authorities: Collection<GrantedAuthority>
) : UserDetails {

    // 추가 데이터 getter 메소드들
    fun getId(): Long = id
    fun getUserId(): String = userId
    fun getRoleId(): Long = roleId

    // UserDetails 인터페이스 구현
    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getPassword(): String = password

    override fun getUsername(): String = userId

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true
}