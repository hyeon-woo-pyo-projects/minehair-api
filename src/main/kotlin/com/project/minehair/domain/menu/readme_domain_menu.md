


# CRUD용 헥사고날 아키텍처 구조

```
domain.menu
├── domain/
│   ├── Menu.kt                         // 도메인 엔티티
│   ├── MenuId.kt                       // 값 객체
│   └── MenuStatus.kt                   // 열거형
├── application/
│   ├── port/
│   │   ├── in/
│   │   │   └── MenuUseCase.kt          // 입력 포트 (CRUD 통합)
│   │   └── out/
│   │       └── MenuPersistencePort.kt  // 출력 포트
│   └── service/
│       └── MenuService.kt              // 애플리케이션 서비스
├── adapter/
│   ├── in/
│   │   └── web/
│   │       └── MenuController.kt       // REST 컨트롤러
│   │       └── dto/
│   │            ├── CreateMenuRequest.kt        // 생성 요청 DTO
│   │            ├── UpdateMenuRequest.kt        // 수정 요청 DTO
│   │            └── MenuResponse.kt             // 응답 DTO
│   └── out/
│       └── persistence/
│           ├── MenuJpaEntity.kt        // JPA 엔티티
│           ├── MenuJpaRepository.kt    // Spring Data JPA Repository
│           ├── MenuMapper.kt           // 도메인 ↔ JPA 매퍼
│           └── MenuPersistenceAdapter.kt // 영속성 어댑터
└── config/
    └── MenuConfig.kt                   // 설정 클래스
```

# 추가 헥사고날 아키텍처 구조

```
domain.menu
├── domain/
│   ├── model/
│   │   ├── Menu.kt                     // 도메인 엔티티
│   │   ├── MenuId.kt                   // 값 객체
│   │   ├── MenuName.kt                 // 값 객체
│   │   ├── Money.kt                    // 값 객체
│   │   └── MenuStatus.kt               // 열거형/값 객체
│   ├── service/
│   │   └── MenuDomainService.kt        // 도메인 서비스 (선택사항)
│   └── exception/
│       ├── MenuNotFoundException.kt    // 도메인 예외
│       └── InvalidMenuException.kt     // 도메인 예외
├── application/
│   ├── port/
│   │   ├── in/
│   │   │   ├── MenuManagementUseCase.kt    // 입력 포트
│   │   │   ├── MenuQueryUseCase.kt         // 입력 포트
│   │   │   └── MenuOrderingUseCase.kt      // 입력 포트
│   │   └── out/
│   │       ├── MenuPersistencePort.kt      // 출력 포트
│   │       ├── MenuEventPublishingPort.kt  // 출력 포트
│   │       └── MenuNotificationPort.kt     // 출력 포트
│   ├── service/
│   │   ├── MenuApplicationService.kt       // 애플리케이션 서비스
│   │   └── MenuQueryService.kt             // 쿼리 서비스
│   └── dto/
│       ├── request/
│       │   ├── CreateMenuRequest.kt        // 요청 DTO
│       │   ├── UpdateMenuRequest.kt        // 요청 DTO
│       │   └── MenuSearchRequest.kt        // 요청 DTO
│       └── response/
│           ├── MenuResponse.kt             // 응답 DTO
│           ├── MenuListResponse.kt         // 응답 DTO
│           └── MenuDetailResponse.kt       // 응답 DTO
├── adapter/
│   ├── in/
│   │   ├── web/
│   │   │   ├── MenuController.kt           // REST 컨트롤러
│   │   │   ├── MenuQueryController.kt      // 쿼리 컨트롤러
│   │   │   └── dto/
│   │   │       ├── MenuWebRequest.kt       // 웹 요청 DTO
│   │   │       └── MenuWebResponse.kt      // 웹 응답 DTO
│   │   ├── messaging/
│   │   │   └── MenuEventConsumer.kt        // 메시지 컨슈머
│   │   └── scheduler/
│   │       └── MenuScheduler.kt            // 스케줄러
│   └── out/
│       ├── persistence/
│       │   ├── entity/
│       │   │   └── MenuJpaEntity.kt        // JPA 엔티티
│       │   ├── repository/
│       │   │   └── MenuJpaRepository.kt    // Spring Data JPA Repository
│       │   ├── mapper/
│       │   │   └── MenuPersistenceMapper.kt // 도메인 ↔ JPA 매퍼
│       │   └── MenuPersistenceAdapter.kt   // 영속성 어댑터
│       ├── messaging/
│       │   ├── MenuEventPublisher.kt       // 이벤트 발행 어댑터
│       │   └── dto/
│       │       └── MenuEventDto.kt         // 이벤트 DTO
│       └── external/
│           ├── MenuNotificationAdapter.kt  // 알림 어댑터
│           └── dto/
│               └── NotificationDto.kt      // 외부 API DTO
└── config/
    ├── MenuConfig.kt                       // 설정 클래스
    └── MenuBeanConfig.kt                   // 빈 설정
```

## 주요 클래스 구현

### 🏗️ Domain Layer

```kotlin
// Menu.kt - 도메인 엔티티
data class Menu(
    val id: MenuId?,
    val parentId: MenuId?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: MenuStatus,
    val createdId: Long,
    val createdAt: LocalDateTime,
    val updatedId: Long,
    val updatedAt: LocalDateTime?
) {
    fun activate() = copy(status = MenuStatus.ACTIVE)
    fun deactivate() = copy(status = MenuStatus.INACTIVE)
    fun hide() = copy(visible = false)
    fun show() = copy(visible = true)
}

// MenuId.kt - 값 객체
@JvmInline
value class MenuId(val value: Long)

// MenuStatus.kt - 열거형
enum class MenuStatus {
    ACTIVE, INACTIVE, DELETED
}
```

### 🔄 Application Layer

```kotlin
// MenuUseCase.kt - 입력 포트 (CRUD 통합)
interface MenuUseCase {
    fun createMenu(request: CreateMenuRequest): MenuResponse
    fun getMenuById(id: MenuId): MenuResponse
    fun getAllMenus(): List<MenuResponse>
    fun updateMenu(id: MenuId, request: UpdateMenuRequest): MenuResponse
    fun deleteMenu(id: MenuId)
}

// MenuPersistencePort.kt - 출력 포트
interface MenuPersistencePort {
    fun save(menu: Menu): Menu
    fun findById(id: MenuId): Menu?
    fun findAll(): List<Menu>
    fun deleteById(id: MenuId)
}

// CreateMenuRequest.kt - 생성 요청 DTO
data class CreateMenuRequest(
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val createdId: Long
)

// UpdateMenuRequest.kt - 수정 요청 DTO
data class UpdateMenuRequest(
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: String,
    val updatedId: Long
)

// MenuResponse.kt - 응답 DTO
data class MenuResponse(
    val id: Long,
    val parentId: Long?,
    val name: String,
    val path: String,
    val orderNo: Int,
    val visible: Boolean,
    val status: String,
    val createdId: Long,
    val createdAt: LocalDateTime,
    val updatedId: Long,
    val updatedAt: LocalDateTime?
)

// MenuService.kt - 애플리케이션 서비스
@Service
@Transactional
class MenuService(
    private val menuPersistencePort: MenuPersistencePort
) : MenuUseCase {

    override fun createMenu(request: CreateMenuRequest): MenuResponse {
        val menu = Menu(
            id = null,
            parentId = request.parentId?.let { MenuId(it) },
            name = request.name,
            path = request.path,
            orderNo = request.orderNo,
            visible = request.visible,
            status = MenuStatus.ACTIVE,
            createdId = request.createdId,
            createdAt = LocalDateTime.now(),
            updatedId = request.createdId,
            updatedAt = null
        )
        
        val savedMenu = menuPersistencePort.save(menu)
        return toResponse(savedMenu)
    }

    override fun getMenuById(id: MenuId): MenuResponse {
        val menu = menuPersistencePort.findById(id)
            ?: throw IllegalArgumentException("Menu not found: ${id.value}")
        return toResponse(menu)
    }

    override fun getAllMenus(): List<MenuResponse> {
        return menuPersistencePort.findAll().map { toResponse(it) }
    }

    override fun updateMenu(id: MenuId, request: UpdateMenuRequest): MenuResponse {
        val existingMenu = menuPersistencePort.findById(id)
            ?: throw IllegalArgumentException("Menu not found: ${id.value}")
        
        val updatedMenu = existingMenu.copy(
            parentId = request.parentId?.let { MenuId(it) },
            name = request.name,
            path = request.path,
            orderNo = request.orderNo,
            visible = request.visible,
            status = MenuStatus.valueOf(request.status),
            updatedId = request.updatedId,
            updatedAt = LocalDateTime.now()
        )
        
        val savedMenu = menuPersistencePort.save(updatedMenu)
        return toResponse(savedMenu)
    }

    override fun deleteMenu(id: MenuId) {
        menuPersistencePort.deleteById(id)
    }
    
    private fun toResponse(menu: Menu): MenuResponse {
        return MenuResponse(
            id = menu.id!!.value,
            parentId = menu.parentId?.value,
            name = menu.name,
            path = menu.path,
            orderNo = menu.orderNo,
            visible = menu.visible,
            status = menu.status.name,
            createdId = menu.createdId,
            createdAt = menu.createdAt,
            updatedId = menu.updatedId,
            updatedAt = menu.updatedAt
        )
    }
}
```

### 🔌 Adapter Layer

```kotlin
// MenuController.kt - REST 컨트롤러
@RestController
@RequestMapping("/api/menus")
class MenuController(
    private val menuUseCase: MenuUseCase
) {
    @PostMapping
    fun createMenu(@RequestBody request: CreateMenuRequest): MenuResponse {
        return menuUseCase.createMenu(request)
    }

    @GetMapping("/{id}")
    fun getMenu(@PathVariable id: Long): MenuResponse {
        return menuUseCase.getMenuById(MenuId(id))
    }

    @GetMapping
    fun getAllMenus(): List<MenuResponse> {
        return menuUseCase.getAllMenus()
    }

    @PutMapping("/{id}")
    fun updateMenu(@PathVariable id: Long, @RequestBody request: UpdateMenuRequest): MenuResponse {
        return menuUseCase.updateMenu(MenuId(id), request)
    }

    @DeleteMapping("/{id}")
    fun deleteMenu(@PathVariable id: Long) {
        menuUseCase.deleteMenu(MenuId(id))
    }
}

// MenuJpaEntity.kt - JPA 엔티티
@Entity
@Table(name = "menu")
data class MenuJpaEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    @Column(name = "parent_id")
    val parentId: Long? = null,
    
    @Column(name = "name", nullable = false, length = 100)
    val name: String,
    
    @Column(name = "path", nullable = false, length = 255)
    val path: String,
    
    @Column(name = "order_no", nullable = false)
    val orderNo: Int = 0,
    
    @Column(name = "visible", nullable = false)
    val visible: Boolean,
    
    @Column(name = "status", nullable = false, length = 20)
    val status: String = "active",
    
    @Column(name = "created_id", nullable = false)
    val createdId: Long,
    
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,
    
    @Column(name = "updated_id", nullable = false)
    val updatedId: Long,
    
    @Column(name = "updated_at")
    val updatedAt: LocalDateTime? = null
)

// MenuJpaRepository.kt - Spring Data JPA Repository
interface MenuJpaRepository : JpaRepository<MenuJpaEntity, Long> {
    fun findByStatus(status: String): List<MenuJpaEntity>
    fun findByVisible(visible: Boolean): List<MenuJpaEntity>
    fun findByParentId(parentId: Long): List<MenuJpaEntity>
}

// MenuMapper.kt - 도메인 ↔ JPA 매퍼
@Component
class MenuMapper {
    
    fun toDomain(entity: MenuJpaEntity): Menu {
        return Menu(
            id = entity.id?.let { MenuId(it) },
            parentId = entity.parentId?.let { MenuId(it) },
            name = entity.name,
            path = entity.path,
            orderNo = entity.orderNo,
            visible = entity.visible,
            status = MenuStatus.valueOf(entity.status.uppercase()),
            createdId = entity.createdId,
            createdAt = entity.createdAt,
            updatedId = entity.updatedId,
            updatedAt = entity.updatedAt
        )
    }
    
    fun toEntity(domain: Menu): MenuJpaEntity {
        return MenuJpaEntity(
            id = domain.id?.value,
            parentId = domain.parentId?.value,
            name = domain.name,
            path = domain.path,
            orderNo = domain.orderNo,
            visible = domain.visible,
            status = domain.status.name.lowercase(),
            createdId = domain.createdId,
            createdAt = domain.createdAt,
            updatedId = domain.updatedId,
            updatedAt = domain.updatedAt
        )
    }
}

// MenuPersistenceAdapter.kt - 영속성 어댑터
@Repository
class MenuPersistenceAdapter(
    private val menuJpaRepository: MenuJpaRepository,
    private val menuMapper: MenuMapper
) : MenuPersistencePort {

    override fun save(menu: Menu): Menu {
        val entity = menuMapper.toEntity(menu)
        val savedEntity = menuJpaRepository.save(entity)
        return menuMapper.toDomain(savedEntity)
    }

    override fun findById(id: MenuId): Menu? {
        return menuJpaRepository.findById(id.value)
            .map { menuMapper.toDomain(it) }
            .orElse(null)
    }

    override fun findAll(): List<Menu> {
        return menuJpaRepository.findAll()
            .map { menuMapper.toDomain(it) }
    }

    override fun deleteById(id: MenuId) {
        menuJpaRepository.deleteById(id.value)
    }
}
```

### ⚙️ Configuration

```kotlin
// MenuConfig.kt - 설정 클래스
@Configuration
@EnableJpaRepositories(basePackages = ["domain.menu.adapter.out.persistence"])
@ComponentScan(basePackages = ["domain.menu"])
class MenuConfig {
    // 필요시 추가 빈 설정
}
```

## 🎯 CRUD API 엔드포인트

```
POST   /api/menus           - 메뉴 생성
GET    /api/menus/{id}      - 메뉴 조회
GET    /api/menus           - 전체 메뉴 조회
PUT    /api/menus/{id}      - 메뉴 수정
DELETE /api/menus/{id}      - 메뉴 삭제
```

이 구조는 **간단한 CRUD 기능**에 최적화되어 있으면서도 **헥사고날 아키텍처의 핵심 원칙**을 유지합니다!


# API 호출시 서비스 흐름

## 📋 전체 흐름도

```
Client Request → MenuController → MenuService → MenuPersistenceAdapter → MenuJpaRepository → Database
                      ↓              ↓                ↓                       ↓
                 Web Adapter    Application      Persistence Adapter    JPA Repository
                   (Inbound)      Service           (Outbound)
```

## 🔄 단계별 상세 흐름

### 1. **메뉴 생성 (POST /api/menus)**

```
1. Client
   ↓ POST /api/menus
   {
     "parentId": 1,
     "name": "상품관리",
     "path": "/products",
     "orderNo": 1,
     "visible": true,
     "createdId": 100
   }

2. MenuController.createMenu()
   ↓ CreateMenuRequest 받음
   ↓ menuUseCase.createMenu(request) 호출

3. MenuService.createMenu()
   ↓ CreateMenuRequest → Menu 도메인 객체 생성
   ↓ menuPersistencePort.save(menu) 호출

4. MenuPersistenceAdapter.save()
   ↓ Menu 도메인 객체 → MenuJpaEntity 변환 (MenuMapper)
   ↓ menuJpaRepository.save(entity) 호출

5. MenuJpaRepository.save()
   ↓ JPA가 SQL 생성 및 실행
   ↓ INSERT INTO menu (parent_id, name, path, ...) VALUES (...)

6. Response Flow (역순)
   Database → MenuJpaEntity → Menu → MenuResponse → Client
```

### 2. **메뉴 조회 (GET /api/menus/{id})**

```
1. Client
   ↓ GET /api/menus/1

2. MenuController.getMenu()
   ↓ PathVariable id = 1
   ↓ menuUseCase.getMenuById(MenuId(1)) 호출

3. MenuService.getMenuById()
   ↓ menuPersistencePort.findById(MenuId(1)) 호출

4. MenuPersistenceAdapter.findById()
   ↓ menuJpaRepository.findById(1) 호출

5. MenuJpaRepository.findById()
   ↓ JPA가 SQL 생성 및 실행
   ↓ SELECT * FROM menu WHERE id = 1

6. Response Flow
   MenuJpaEntity → Menu → MenuResponse → Client
```

### 3. **메뉴 수정 (PUT /api/menus/{id})**

```
1. Client
   ↓ PUT /api/menus/1
   {
     "parentId": 1,
     "name": "상품관리_수정",
     "path": "/products-updated",
     "orderNo": 2,
     "visible": false,
     "status": "INACTIVE",
     "updatedId": 200
   }

2. MenuController.updateMenu()
   ↓ PathVariable id = 1, UpdateMenuRequest
   ↓ menuUseCase.updateMenu(MenuId(1), request) 호출

3. MenuService.updateMenu()
   ↓ 1) menuPersistencePort.findById(MenuId(1)) 호출 (기존 데이터 조회)
   ↓ 2) 기존 Menu 객체 + 수정 데이터로 새 Menu 객체 생성
   ↓ 3) menuPersistencePort.save(updatedMenu) 호출

4. MenuPersistenceAdapter
   ↓ findById() → save() 순서로 실행

5. Database
   ↓ SELECT → UPDATE 실행
```

## 🎯 핵심 컴포넌트별 역할

### **MenuController** (Inbound Adapter)
```kotlin
@RestController
class MenuController(
    private val menuUseCase: MenuUseCase  // 포트 의존성
) {
    // HTTP 요청 → Application 요청으로 변환
    // Application 응답 → HTTP 응답으로 변환
}
```

### **MenuService** (Application Service)
```kotlin
@Service
class MenuService(
    private val menuPersistencePort: MenuPersistencePort  // 포트 의존성
) : MenuUseCase {
    // 비즈니스 로직 조합
    // 트랜잭션 경계 설정
    // 도메인 규칙 적용
}
```

### **MenuPersistenceAdapter** (Outbound Adapter)
```kotlin
@Repository
class MenuPersistenceAdapter(
    private val menuJpaRepository: MenuJpaRepository,  // 기술 의존성
    private val menuMapper: MenuMapper
) : MenuPersistencePort {
    // 도메인 객체 ↔ JPA 엔티티 변환
    // 영속성 기술 캡슐화
}
```

## 🔄 의존성 방향

```
Controller → UseCase ← Service → Port ← Adapter → Repository
  (Web)      (Interface)  (Impl)   (Interface)  (Impl)    (JPA)
```

## 📊 데이터 변환 흐름

### **요청 흐름**
```
HTTP Request → CreateMenuRequest → Menu(Domain) → MenuJpaEntity → Database
```

### **응답 흐름**
```
Database → MenuJpaEntity → Menu(Domain) → MenuResponse → HTTP Response
```

## 🎭 각 계층의 책임

### **Presentation Layer** (Controller)
- HTTP 요청/응답 처리
- 데이터 검증 (기본적인)
- 인증/인가 처리

### **Application Layer** (Service)
- 비즈니스 로직 조합
- 트랜잭션 관리
- 도메인 규칙 적용

### **Domain Layer** (Domain Objects)
- 핵심 비즈니스 규칙
- 데이터 무결성 보장
- 도메인 로직 캡슐화

### **Infrastructure Layer** (Persistence)
- 데이터 영속성 처리
- 외부 시스템 연동
- 기술적 세부사항 처리

## 🚀 트랜잭션 흐름

```
@Transactional (MenuService)
├── 1. 트랜잭션 시작
├── 2. 비즈니스 로직 실행
├── 3. 영속성 계층 호출
├── 4. 성공시 커밋 / 실패시 롤백
└── 5. 트랜잭션 종료
```

이렇게 **헥사고날 아키텍처**에서는 각 계층이 **명확한 책임**을 가지고 **의존성 방향**을 준수하면서 API 요청을 처리합니다!