package com.project.minehair.domain.healthcheck.adapter.inbound.web

import com.project.minehair.domain.healthcheck.application.port.inbound.HealthCheckUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class HealthCheckController (
    private val healthCheckUseCase: HealthCheckUseCase
){
    @GetMapping("/health")
    fun checkHealth(): String {
        return healthCheckUseCase.checkHealth()
    }
}