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