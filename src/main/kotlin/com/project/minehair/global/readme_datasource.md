# 📘 RoutingDataSource 전체 구성 문서 (Kotlin + Spring Boot)

## 🧩 목적

이 구성은 하나의 Repository 계층을 유지하면서도, 트랜잭션의 readOnly 여부에 따라 **자동으로 READ / WRITE DataSource를 선택**하는 방식입니다.

헥사고날 아키텍처 구조에서 도메인 공통 기능으로 `global` 패키지 하위에 구성됩니다.

---

## 🔁 전체 동작 흐름

1. 서비스 메서드 실행 → `@Transactional(readOnly = true)` 여부 AOP 감지
2. AOP에서 `DataSourceContextHolder`에 현재 타입(`READ`/`WRITE`) 저장
3. JPA가 DB 접근을 시도할 때 → `RoutingDataSource`가 context 기반으로 연결 DB 결정
4. 트랜잭션 종료 후 → AOP에서 context 초기화

---

## 📁 디렉토리 구조

```
global/
├── config/
│   └── RoutingDataSourceConfig.kt
├── datasource/
│   ├── DataSourceContextHolder.kt
│   ├── DataSourceType.kt
│   └── RoutingDataSource.kt
└── aop/
    └── ReadOnlyRoutingAspect.kt
```

---

## 🔍 클래스별 상세 설명

---

### ✅ DataSourceType.kt

```kotlin
enum class DataSourceType {
    WRITE, READ
}
```

- `RoutingDataSource`에서 key로 사용하는 enum
- 기본값은 WRITE

---

### ✅ DataSourceContextHolder.kt

```kotlin
object DataSourceContextHolder {
    private val contextHolder = ThreadLocal<DataSourceType>()

    fun set(type: DataSourceType) = contextHolder.set(type)
    fun get(): DataSourceType? = contextHolder.get()
    fun clear() = contextHolder.remove()
}
```

- ThreadLocal로 현재 트랜잭션에 사용될 DB 타입을 보관
- AOP에서 이 값을 세팅하고, `RoutingDataSource`에서 읽음

---

### ✅ RoutingDataSource.kt

```kotlin
class RoutingDataSource(
    private val writeDataSource: DataSource,
    private val readDataSource: DataSource
) : AbstractRoutingDataSource() {

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
```

- 스프링이 내부적으로 DataSource를 사용할 때, 이 클래스가 어떤 DataSource를 쓸지 결정
- `afterPropertiesSet()`은 DataSource 맵 구성용 (READ / WRITE)

---

### ✅ RoutingDataSourceConfig.kt

```kotlin
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    basePackages = ["com.project.repository"],
    entityManagerFactoryRef = "entityManagerFactory",
    transactionManagerRef = "transactionManager"
)
class RoutingDataSourceConfig {
    ...
}
```

- `@Primary`로 등록해 JPA가 기본으로 사용할 DataSource 지정
- `EntityManagerFactory`, `TransactionManager` 모두 라우팅 DataSource 사용
- HikariCP 공통 설정은 `spring.datasource.hikari`에서 불러옴

---

### ✅ ReadOnlyRoutingAspect.kt

```kotlin
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
```

- `@Transactional(readOnly = true)`이면 → READ
- 아니면 → WRITE
- 트랜잭션 종료 후에는 반드시 `clear()`로 ThreadLocal 해제

---

## ✅ 정리

| 구성 요소 | 설명 |
|-----------|------|
| DataSourceType | 읽기/쓰기 판단 enum |
| DataSourceContextHolder | 현재 쓰레드 기준 컨텍스트 저장소 |
| RoutingDataSource | 실질적인 DataSource 결정 클래스 |
| RoutingDataSourceConfig | DataSource + JPA 설정 |
| ReadOnlyRoutingAspect | readOnly 여부 감지 및 분기 |

---

## 📌 참고 사항

- Repository 클래스는 단일 구성 (read/write로 나누지 않음)
- 트랜잭션 기반으로만 분기함
- 향후 MySQL, PostgreSQL의 master/slave 분기에도 동일 패턴으로 확장 가능

> 📎 이 문서는 헥사고날 구조 기반 아키텍처에서 `global` 계층에 위치하는 공통 DB 접근 분기 전략 문서입니다.