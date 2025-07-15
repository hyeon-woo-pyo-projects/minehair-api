package com.project.minehair.global.datasource

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource
import javax.sql.DataSource

class RoutingDataSource (
    private val writeDataSource: DataSource,
    private val readDataSource: DataSource
): AbstractRoutingDataSource() {

    override fun determineCurrentLookupKey(): Any {
        return DataSourceContextHolder.get() ?: DataSourceType.WRITE
    }

    override fun afterPropertiesSet() {
        val targetDataSources = mapOf<Any, Any>(
            DataSourceType.WRITE to writeDataSource,
            DataSourceType.READ to readDataSource
        )
        setTargetDataSources(targetDataSources)
        setDefaultTargetDataSource(writeDataSource)
        super.afterPropertiesSet()
    }

}