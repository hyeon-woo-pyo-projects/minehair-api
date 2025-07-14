package com.project.minehair.healthcheck.application.port.inbound


fun interface HealthCheckUseCase {
    fun checkHealth(): String
}