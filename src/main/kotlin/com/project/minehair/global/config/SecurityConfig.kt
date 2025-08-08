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
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
class SecurityConfig(
    private val customUserDetailsService: CustomUserDetailsService,
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .cors { it.configurationSource(corsConfigurationSource()) }
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
                        "/api/v3/api-docs/**",
                        "/api/swagger-ui/**",
                        "/api/swagger-ui.html",
                        "/swagger-resources/**",
                        "/webjars/**"
                    ).permitAll() // Swagger 관련 API 접근 허용
                    .requestMatchers("/health/**").permitAll() // 헬스 체크 API 접근 허용
                    .requestMatchers("/api/auth/**").permitAll()  // 로그인/회원가입은 토큰 불필요
                    .requestMatchers(HttpMethod.GET, "/api/role-menus/**").permitAll() // 공개 API
                    .requestMatchers(HttpMethod.POST, "/api/user/**").permitAll() // 사용자 조회는 공개
                    .requestMatchers(HttpMethod.GET, "/api/banner/post").permitAll() // 게시상태의 배너 조회는 공개
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

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowedOrigins = listOf(
            "http://localhost:3000",
            "https://minehair401.com",          // 추가!
            "https://www.minehair401.com"       // 추가!
        )
        configuration.allowedMethods = listOf("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
        configuration.allowedHeaders = listOf("*")
        configuration.allowCredentials = true
        configuration.maxAge = 3600L

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }

}