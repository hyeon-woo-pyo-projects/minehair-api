CREATE TABLE IF NOT EXISTS menu (
    id bigint auto_increment primary key comment 'ID',
    parent_id bigint null comment '부모 메뉴 ID',
    name varchar(100) not null comment '메뉴 이름',
    path varchar(255) not null comment '메뉴 경로',
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
    code varchar(50) not null unique comment '역할 코드 (예: ADMIN, USER, GUEST)',
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