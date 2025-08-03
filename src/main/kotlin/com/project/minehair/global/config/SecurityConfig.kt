package com.project.minehair.global.config

import com.project.minehair.global.filter.JwtAuthenticationFilter
import com.project.minehair.global.security.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
class SecurityConfig(
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .csrf { it.disable() }
            .formLogin { it.disable() }
            .httpBasic { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .headers { headers ->
                headers.frameOptions { it.sameOrigin() } // H2 콘솔의 iframe 허용
            }
            .authorizeHttpRequests { auth ->
                auth
                    .requestMatchers("/h2-console/**").permitAll() // H2 콘솔 접근 허용
                    .requestMatchers(
                        "/v3/api-docs/**"
                        , "/swagger-ui/**"
                        , "/swagger-ui.html"
                        , "/swagger-resources/**"
                        , "/webjars/**"
                    ).permitAll()  //swagger UI 접근 허용
                    .requestMatchers("/api/auth/**").permitAll()  // 로그인/회원가입은 토큰 불필요
                    .requestMatchers(HttpMethod.GET, "/api/role-menus/**").permitAll() // 공개 API
                    .anyRequest().authenticated()  // 나머지는 인증 필요
            }
            .authenticationProvider(authenticationProvider())
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .exceptionHandling { exceptions ->
                exceptions
                    .authenticationEntryPoint { _, response, _ ->
                        response.status = 401
                        response.characterEncoding = "UTF-8"
                        response.contentType = "application/json; charset=UTF-8"
                        response.writer.write("""{"error": "Unauthorized", "message": "인증이 필요합니다"}""")
                    }
                    .accessDeniedHandler { _, response, _ ->
                        response.status = 403
                        response.characterEncoding = "UTF-8"
                        response.contentType = "application/json; charset=UTF-8"
                        response.writer.write("""{"error": "Forbidden", "message": "접근 권한이 없습니다"}""")
                    }
            }
        return http.build()
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }

    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val daoAuthenticationProvider = DaoAuthenticationProvider()
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder())
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService)
        return daoAuthenticationProvider
    }

    @Bean
    fun authenticationManager(config: AuthenticationConfiguration): AuthenticationManager {
        return config.authenticationManager
    }

}