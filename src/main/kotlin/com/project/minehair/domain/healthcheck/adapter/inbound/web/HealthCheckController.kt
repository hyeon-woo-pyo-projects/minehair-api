package com.project.minehair.domain.healthcheck.adapter.inbound.web

import com.project.minehair.domain.healthcheck.application.port.inbound.HealthCheckUseCase
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(origins = ["http://localhost:3000"])
@RestController
class HealthCheckController (
    private val healthCheckUseCase: HealthCheckUseCase
){
    @GetMapping("/health")
    fun checkHealth(): String {
        return healthCheckUseCase.checkHealth()
    }
}