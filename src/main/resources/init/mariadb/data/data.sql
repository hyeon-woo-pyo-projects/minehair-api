-- [role] 테이블 초기 데이터 삽입 -----------------------------------------------------------------------------------------
INSERT INTO role (code, name, description, priority, status, created_id, created_at, updated_id)
VALUES
('ROLE_ADMIN', '관리자', '전체 시스템을 제어할 수 있는 최고 관리자', 1, 'active', 1, NOW(), 1),
('ROLE_USER', '회원', '로그인한 일반 사용자', 2, 'active', 1, NOW(), 1),
('ROLE_GUEST', '비회원', '로그인하지 않은 사용자', 3, 'active', 1, NOW(), 1);
-----------------------------------------------------------------------------------------------------------------------
-- [users] 테이블 초기 데이터 삽입 ------------------------------------------------------------------------------------
INSERT INTO users (
    role_id,
    user_id,
    password,
    name,
    phone,
    phone_hash,
    email,
    status,
    created_id,
    created_at,
    updated_id,
    updated_at
) VALUES (
    1,
    'system',
    '$2a$10$nZDAWUA7.IVQn62NSya4ueQaV.eRGFrweOHTWMvAtRtrSwMZwcl5S',
    '시스템',
    '010-0000-0000',
    '',
    'system@example.com',
    'active',
    1,
    NOW(),
    1,
    NULL
);
-----------------------------------------------------------------------------------------------------------------------
-- [menu] 테이블 초기 데이터 삽입 ------------------------------------------------------------------------------------------
INSERT INTO menu (parent_id, name, path, image_url, is_visible, menu_type, order_no, status, created_id, created_at, updated_id)
VALUES
(NULL, '소개', '/about', null, true, 'MAJOR', 10, 'active', 1, NOW(), 1),
(NULL, '염색', '/color', null, true, 'MAJOR', 20, 'active', 1, NOW(), 1),
(NULL, '탈색', '/bleach', null, true, 'MAJOR', 30, 'active', 1, NOW(), 1),
(NULL, '클리닉', '/clinic', null, true, 'MAJOR', 40, 'active', 1, NOW(), 1),
(NULL, '상담/예약', '/contact', null, true, 'MAJOR', 50, 'active', 1, NOW(), 1),
(NULL, 'Review', '/review', null, true, 'MAJOR', 60, 'active', 1, NOW(), 1),
(NULL, 'Q&A', '/qna', null, true, 'MAJOR', 70, 'active', 1, NOW(), 1),
(NULL, 'Community', '/community', null, true, 'MAJOR', 80, 'active', 1, NOW(), 1)
;


INSERT INTO menu (parent_id, name, path, image_url, is_visible, menu_type, order_no, status, created_id, created_at, updated_id)
VALUES
(4, '모발', '/clinic/hair', null, true, 'MINOR', 1, 'active', 1, NOW(), 1)
;


INSERT INTO menu (parent_id, name, path, image_url, is_visible, menu_type, order_no, status, created_id, created_at, updated_id)
VALUES
(9, '리바이탈', '/clinic/hair/revital', null, true, 'SUB', 1, 'active', 1, NOW(), 1)
;
-----------------------------------------------------------------------------------------------------------------------
-- [role_menu] 테이블 초기 데이터 삽입 ------------------------------------------------------------------------------------
-- 1. 관리자(Admin, role_id = 1): 전체 메뉴 접근 허용
INSERT INTO role_menu (role_id, menu_id, status, created_id, created_at, updated_id)
SELECT 1, m.id, 'active', 1, NOW(), 1
FROM menu m
WHERE NOT EXISTS (
    SELECT 1 FROM role_menu rm
    WHERE rm.role_id = 1 AND rm.menu_id = m.id
);

-- 2. 회원(User, role_id = 2): 특정 메뉴만 접근 허용
INSERT INTO role_menu (role_id, menu_id, status, created_id, created_at, updated_id)
SELECT 2, m.id, 'active', 1, NOW(), 1
FROM menu m
WHERE m.name IN (
    '클리닉', '모발', '리바이탈',
    '상담/예약',
    'Review'
)
AND NOT EXISTS (
    SELECT 1 FROM role_menu rm
    WHERE rm.role_id = 2 AND rm.menu_id = m.id
);

-- 3. 비회원(Guest, role_id = 3): 소개, Review만 접근 허용
INSERT INTO role_menu (role_id, menu_id, status, created_id, created_at, updated_id)
SELECT 3, m.id, 'active', 1, NOW(), 1
FROM menu m
WHERE m.name IN ('소개', 'Review')
AND NOT EXISTS (
    SELECT 1 FROM role_menu rm
    WHERE rm.role_id = 3 AND rm.menu_id = m.id
);
-----------------------------------------------------------------------------------------------------------------------
-- [banner] 테이블 초기 데이터 삽입 ---------------------------------------------------------------------------------------
INSERT INTO banner (
    content,
    color,
    text_color,
    link,
    image_url,
    is_post,
    status,
    created_id,
    created_at,
    updated_id,
    updated_at
) VALUES (
    '새로운 배너를 추가해보세요.',
    '#FF5733',
    '#FFFFFF',
    'https://example.com',
    NULL,
    TRUE,
    'active',
    1,
    NOW(),
    1,
    NULL
);
-----------------------------------------------------------------------------------------------------------------------
-- [banner] 테이블 초기 데이터 삽입 ---------------------------------------------------------------------------------------
INSERT INTO consultation_category (
    code,
    name,
    status,
    created_id,
    created_at,
    updated_id,
    updated_at
) VALUES
('root_bleaching','뿌리 탈색','active',1,NOW(),1,NULL),
('full_bleaching','전체 탈색','active',1,NOW(),1,NULL),
('root_coloring','뿌리 염색','active',1,NOW(),1,NULL),
('full_coloring','전체 염색','active',1,NOW(),1,NULL);
-----------------------------------------------------------------------------------------------------------------------