package com.project.minehair.healthcheck.domain.service

import com.project.minehair.healthcheck.application.port.inbound.HealthCheckUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class HealthCheckService : HealthCheckUseCase {
    override fun checkHealth(): String {
        return  "${LocalDateTime.now()} health check ok"
    }
}