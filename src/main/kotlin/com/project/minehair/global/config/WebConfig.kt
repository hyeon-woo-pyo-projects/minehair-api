package com.project.minehair.global.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/**")
//            .allowedOriginPatterns("*") // 모든 도메인 허용 (개발용)
            .allowedOrigins("http://localhost:3000") // 운영시 특정 도메인만 허용
            .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
            .maxAge(3600) // preflight 요청 캐시 시간 (초)
    }
}