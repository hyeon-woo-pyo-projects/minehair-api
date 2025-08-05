CREATE TABLE IF NOT EXISTS menu (
    id bigint auto_increment primary key comment 'ID',
    parent_id bigint null comment '부모 메뉴 ID',
    name varchar(100) not null comment '메뉴 이름',
    path varchar(255) not null comment '메뉴 경로',
    image_url varchar(255) null comment '메뉴 이미지 URL',
    order_no int not null DEFAULT 0 comment '정렬 순서',
    visible boolean not null comment '표시 여부',
    status varchar(20) not null default 'active' comment '상태',
    created_id bigint not null comment '생성자 ID',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_id bigint not null comment '수정자 ID',
    updated_at datetime null comment '수정 시간'
) comment '메뉴' CHARSET=utf8mb4;
-- 제약 조건 제거
ALTER TABLE menu DROP CONSTRAINT IF EXISTS uq_menu_name_parent;
-- 제약 조건 추가
ALTER TABLE menu ADD CONSTRAINT uq_menu_name_parent UNIQUE (name, parent_id);

CREATE TABLE IF NOT EXISTS role (
    id bigint auto_increment primary key comment 'ID',
    code varchar(50) not null unique comment '역할 코드 (예: ROLE_ADMIN, ROLE_USER, ROLE_GUEST)',
    name varchar(100) not null comment '역할 이름',
    description varchar(255) null comment '역할 설명',
    priority int not null default 0 comment '역할 우선순위',
    status varchar(20) not null default 'active' comment '상태',
    created_id bigint not null comment '생성자 ID',
    created_at datetime not null default current_timestamp comment '생성 시간',
    updated_id bigint not null comment '수정자 ID',
    updated_at datetime null comment '수정 시간'
) comment '역할' CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS role_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    role_id BIGINT NOT NULL COMMENT '역할 ID',
    menu_id BIGINT NOT NULL COMMENT '메뉴 ID',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL COMMENT '수정 시간',
    CONSTRAINT uq_role_menu UNIQUE (role_id, menu_id)
) COMMENT '역할별 메뉴 접근 권한' CHARSET=utf8mb4;

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
    updated_at DATETIME NULL COMMENT '수정 시간',
    CONSTRAINT uq_users_user_id_status UNIQUE (user_id, status),
    CONSTRAINT uq_users_phone_hash UNIQUE (phone_hash)
) COMMENT '사용자 테이블 (회원/관리자 통합)' CHARSET=utf8mb4;

-- 커스텀배너 테이블
CREATE TABLE IF NOT EXISTS banner (
    id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT 'ID',
    content TEXT NOT NULL COMMENT '배너 내용',
    color VARCHAR(10) NOT NULL COMMENT '배너 색상',
    link TEXT NOT NULL COMMENT '배너 링크',
    is_post BOOLEAN NOT NULL DEFAULT false COMMENT '게시 여부',
    status VARCHAR(20) NOT NULL DEFAULT 'active' COMMENT '상태',
    created_id BIGINT NOT NULL COMMENT '생성자 ID',
    created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 시간',
    updated_id BIGINT NOT NULL COMMENT '수정자 ID',
    updated_at DATETIME NULL COMMENT '수정 시간'
) COMMENT '배너 테이블' CHARSET=utf8mb4;