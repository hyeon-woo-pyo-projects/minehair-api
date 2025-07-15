package com.project.minehair.domain.healthcheck.domain.service

import com.project.minehair.domain.healthcheck.application.port.inbound.HealthCheckUseCase
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class HealthCheckService : HealthCheckUseCase {
    override fun checkHealth(): String {
        return  "${LocalDateTime.now()} health check ok"
    }
}