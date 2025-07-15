package com.project.minehair.domain.healthcheck.application.port.inbound


fun interface HealthCheckUseCase {
    fun checkHealth(): String
}