-- [menu] 테이블 초기 데이터 삽입 ------------------------------------------------------------------------------------------
-- 대카테고리: parent_id = NULL
INSERT IGNORE INTO menu (parent_id, name, path, order_no, visible, status, created_id, created_at, updated_id)
VALUES
(NULL, '소개', '/about', 1, true, 'active', 1, NOW(), 1),
(NULL, '염색', '/color', 2, true, 'active', 1, NOW(), 1),
(NULL, '탈색', '/bleach', 3, true, 'active', 1, NOW(), 1),
(NULL, '클리닉', '/clinic', 4, true, 'active', 1, NOW(), 1),
(NULL, '상담/예약', '/contact', 5, true, 'active', 1, NOW(), 1),
(NULL, 'Review', '/review', 6, true, 'active', 1, NOW(), 1),
(NULL, 'Community', '/community', 7, true, 'active', 1, NOW(), 1);

-- 중카테고리/소카테고리: parent_id를 정확히 지정해야 함
-- 중복되면 무시됨 (name + parent_id 기준)
INSERT INTO menu (parent_id, name, path, order_no, visible, status, created_id, created_at, updated_id)
SELECT m.id, '모발', '/clinic/hair', 1, true, 'active', 1, NOW(), 1
FROM menu m
WHERE m.name = '클리닉'
  AND NOT EXISTS (
    SELECT 1 FROM menu WHERE name = '모발' AND parent_id = m.id
);

-- 소카테고리들
INSERT INTO menu (parent_id, name, path, order_no, visible, status, created_id, created_at, updated_id)
SELECT m.id, '리바이탈', '/clinic/hair/revital', 1, true, 'active', 1, NOW(), 1
FROM menu m WHERE m.name = '모발'
  AND NOT EXISTS (SELECT 1 FROM menu WHERE name = '리바이탈' AND parent_id = m.id);
-----------------------------------------------------------------------------------------------------------------------

-- [role] 테이블 초기 데이터 삽입 -----------------------------------------------------------------------------------------
INSERT IGNORE INTO role (code, name, description, priority, status, created_id, created_at, updated_id)
VALUES
('ADMIN', '관리자', '전체 시스템을 제어할 수 있는 최고 관리자', 1, 'active', 1, NOW(), 1),
('USER', '회원', '로그인한 일반 사용자', 2, 'active', 1, NOW(), 1),
('GUEST', '비회원', '로그인하지 않은 사용자', 3, 'active', 1, NOW(), 1);
-----------------------------------------------------------------------------------------------------------------------
