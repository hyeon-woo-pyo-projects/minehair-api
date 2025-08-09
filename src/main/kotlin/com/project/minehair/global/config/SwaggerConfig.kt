package com.project.minehair.global.config

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.security.SecurityScheme
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Minehair API")
                    .version("1.0")
                    .description("Minehair API Documentation")
            )
            .servers(
                listOf(
                    Server().url("https://minehair401.com").description("Production Server"),
                    Server().url("http://minehair401.com").description("Production Server"),
                    Server().url("http://localhost:8080").description("Local Development Server")
                )
            )
            .components(
                Components().addSecuritySchemes(
                    "Authorization",
                    SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .`in`(SecurityScheme.In.HEADER)
                        .name("Authorization")
                )
            )
            .security(listOf(SecurityRequirement().addList("Authorization")))
    }
}