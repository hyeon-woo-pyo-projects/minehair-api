package com.project.minehair.global.config

import com.project.minehair.global.datasource.RoutingDataSource
import jakarta.persistence.EntityManagerFactory
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.orm.jpa.JpaTransactionManager
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import org.springframework.transaction.PlatformTransactionManager
import javax.sql.DataSource

@Configuration
class RoutingDataSourceConfig {

    @Bean
    @ConfigurationProperties("spring.datasource.write")
    fun writeDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    @ConfigurationProperties("spring.datasource.read")
    fun readDataSourceProperties(): DataSourceProperties = DataSourceProperties()

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun writeDataSource(): DataSource =
        writeDataSourceProperties().initializeDataSourceBuilder().build()

    @Bean
    @ConfigurationProperties("spring.datasource.hikari")
    fun readDataSource(): DataSource =
        readDataSourceProperties().initializeDataSourceBuilder().build()

    @Primary
    @Bean
    fun routingDataSource(): DataSource =
        RoutingDataSource(writeDataSource(), readDataSource())

    @Primary
    @Bean
    fun entityManagerFactory(
        builder: EntityManagerFactoryBuilder
    ): LocalContainerEntityManagerFactoryBean =
        builder
            .dataSource(routingDataSource())
            .packages("com.project.minehair.domain")
            .persistenceUnit("default")
            .build()

    @Primary
    @Bean
    fun transactionManager(
        @Qualifier("entityManagerFactory") emf: EntityManagerFactory
    ): PlatformTransactionManager = JpaTransactionManager(emf)
}