package com.project.minehair.global.datasource

object DataSourceContextHolder {
    private val contextHolder = ThreadLocal<DataSourceType>()

    fun set(type: DataSourceType) {
        contextHolder.set(type)
    }

    fun get(): DataSourceType? = contextHolder.get()

    fun clear() {
        contextHolder.remove()
    }
}