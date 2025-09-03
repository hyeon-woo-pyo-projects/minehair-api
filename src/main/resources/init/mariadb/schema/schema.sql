-- 메뉴 테이블
CREATE TABLE IF NOT EXISTS menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    parent_id BIGINT NULL COMMENT '부모 메뉴 ID',
    name VARCHAR(100) NOT NULL COMMENT '메뉴 이름',
    path VARCHAR(255) NOT NULL COMMENT '메뉴 경로',
    image_url VARCHAR(255) NULL COMMENT '메뉴 이미지 URL',
    is_visible BOOLEAN NOT NULL DEFAULT TRUE COMMENT '메뉴 표시 여부',
    menu_type VARCHAR(20) NOT NULL COMMENT '메뉴 타입 (대(MAJOR), 중(MINOR), 소(SUB))',
    order_no INT NOT NULL DEFAULT 0 COMMENT '정렬 순서',
    is_manage BOOLEAN NOT NULL DEFAULT TRUE comment '관리 여부',
    is_contents boolean BOOLEAN NOT NULL DEFAULT comment '컨텐츠 메뉴 여부',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='메뉴' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 역할 테이블
CREATE TABLE IF NOT EXISTS role (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    code VARCHAR(50) NOT NULL UNIQUE COMMENT '역할 코드 (예: ROLE_ADMIN, ROLE_USER, ROLE_GUEST)',
    name VARCHAR(100) NOT NULL COMMENT '역할 이름',
    description VARCHAR(255) NULL COMMENT '역할 설명',
    priority INT NOT NULL DEFAULT 0 COMMENT '역할 우선순위',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='역할' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 역할별 메뉴 접근 권한 테이블
CREATE TABLE IF NOT EXISTS role_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    role_id BIGINT NOT NULL COMMENT '역할 ID',
    menu_id BIGINT NOT NULL COMMENT '메뉴 ID',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='역할별 메뉴 접근 권한' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 사용자 테이블 (회원과 관리자 통합)
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    role_id BIGINT NOT NULL COMMENT '역할 ID',
    user_id VARCHAR(100) NOT NULL COMMENT '사용자명',
    password VARCHAR(255) NOT NULL COMMENT '비밀번호',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    phone VARCHAR(255) NOT NULL COMMENT '전화번호',
    phone_hash VARCHAR(255) NOT NULL COMMENT '전화번호 해시',
    email VARCHAR(255) NULL COMMENT '이메일',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='사용자 테이블 (회원/관리자 통합)' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 배너 테이블
CREATE TABLE IF NOT EXISTS banner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    content TEXT NOT NULL COMMENT '배너 내용',
    color VARCHAR(10) NOT NULL COMMENT '배너 색상',
    text_color VARCHAR(10) NOT NULL COMMENT '배너 텍스트 색상',
    link TEXT NOT NULL COMMENT '배너 링크',
    image_url TEXT NULL COMMENT '배너 이미지 URL',
    is_post BOOLEAN NOT NULL DEFAULT FALSE COMMENT '게시 여부',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='배너 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 상담 항목 테이블
CREATE TABLE IF NOT EXISTS consultation_category (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    code VARCHAR(50) NOT NULL COMMENT '상담 항목 코드',
    name VARCHAR(255) NOT NULL COMMENT '상담 항목 이름',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='상담 항목 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 상담 접수 테이블
CREATE TABLE IF NOT EXISTS consultation_reception (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    category_id BIGINT NOT NULL COMMENT '상담 항목 ID',
    name VARCHAR(100) NOT NULL COMMENT '이름',
    phone VARCHAR(255) NOT NULL COMMENT '전화번호',
    phone_hash VARCHAR(255) NOT NULL COMMENT '전화번호 해시',
    reception_state VARCHAR(20) NOT NULL DEFAULT 'PENDING' COMMENT '접수 상태',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='상담 접수 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 홈 슬라이드 테이블
CREATE TABLE IF NOT EXISTS home_slide (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    image_url TEXT NOT NULL COMMENT '이미지 URL',
    link TEXT NOT NULL COMMENT '링크',
    order_no INT NOT NULL DEFAULT 0 COMMENT '정렬 순서',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'

) COMMENT='홈 슬라이드 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- QNA 게시판 테이블
CREATE TABLE IF NOT EXISTS board_qna (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    title VARCHAR(200) NOT NULL COMMENT '제목',
    content TEXT NOT NULL COMMENT '내용',
    author VARCHAR(50) NOT NULL COMMENT '작성자',
    view_count INT DEFAULT 0 COMMENT '조회수',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='QNA 게시판 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- Review 게시판 테이블
CREATE TABLE IF NOT EXISTS board_review (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    title VARCHAR(200) NOT NULL COMMENT '제목',
    content TEXT NOT NULL COMMENT '내용',
    author VARCHAR(50) NOT NULL COMMENT '작성자',
    view_count INT DEFAULT 0 COMMENT '조회수',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '수정 시간'
) COMMENT='Review 게시판 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 쿠폰 테이블
CREATE TABLE IF NOT EXISTS coupon (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    content TEXT NOT NULL COMMENT '쿠폰 내용',
    condition_type VARCHAR(50) NOT NULL COMMENT '조건 타입(구입액, 생일 등)',
    discount_type VARCHAR(50) NOT NULL COMMENT '할인 타입(정액, 정률)',
    discount_amount INT NOT NULL COMMENT '할인 값',
    period_start DATE NOT NULL COMMENT '시작 기간',
    period_end DATE NOT NULL COMMENT '종료 기간',
    is_post BOOLEAN NOT NULL DEFAULT false COMMENT '게시 여부',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL COMMENT '수정 시간'
) COMMENT='쿠폰 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 페이지 컨텐츠 테이블
CREATE TABLE IF NOT EXISTS page_contents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    menu_id INT NOT NULL DEFAULT 0 COMMENT '컨텐츠용 메뉴 id',
    page_url TEXT NOT NULL COMMENT '컨텐츠용 페이지 URL',
    contents_type VARCHAR(50) NOT NULL COMMENT '컨텐츠 타입(IMAGE,VIDEO)',
    contents_url TEXT NOT NULL COMMENT '컨텐츠 URL',
    order_no INT NOT NULL DEFAULT 0 comment '정렬 순서',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL COMMENT '수정 시간'
) COMMENT '페이지 컨텐츠 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- 이벤트 페이지 테이블
CREATE TABLE IF NOT EXISTS event_page_contents (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    order_no INT NOT NULL DEFAULT 0 comment '정렬 순서',
    slide_order_no INT NOT NULL DEFAULT 0 comment '정렬 순서',
    image_url TEXT NOT NULL COMMENT '이미지 URL',
    link_url TEXT NOT NULL COMMENT '연결 URL',
    text_content TEXT NULL COMMENT '텍스트 내용',
    is_add_post BOOLEAN NOT NULL DEFAULT false COMMENT '게시 여부',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL COMMENT '수정 시간'
) COMMENT '이벤트 페이지 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- sns 플렛폼 테이블
CREATE TABLE IF NOT EXISTS sns_platform (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    order_no INT NOT NULL DEFAULT 0 comment '정렬 순서',
    image_url TEXT NOT NULL COMMENT '이미지 URL',
    link_url TEXT NOT NULL COMMENT '연결 URL',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL COMMENT '수정 시간'
) COMMENT 'sns 플렛폼 테이블' CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;