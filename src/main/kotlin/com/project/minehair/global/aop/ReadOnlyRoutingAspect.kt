package com.project.minehair.global.aop

import com.project.minehair.global.datasource.DataSourceContextHolder
import com.project.minehair.global.datasource.DataSourceType
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Aspect
@Component
class ReadOnlyRoutingAspect {

    @Pointcut("execution(* com.project..*Service.*(..))")
    fun serviceMethods() {}

    @Before("serviceMethods() && @annotation(tx)")
    fun before(tx: Transactional) {
        if (tx.readOnly) {
            DataSourceContextHolder.set(DataSourceType.READ)
        } else {
            DataSourceContextHolder.set(DataSourceType.WRITE)
        }
    }

    @After("serviceMethods()")
    fun after() {
        DataSourceContextHolder.clear()
    }

}