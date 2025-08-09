#!/bin/bash

# 설정 변수
IMAGE_NAME="minehair-api"
CONTAINER_NAME="minehair-api"
HOST_IMAGE_PATH="/root/minehair/images"

echo "Docker 이미지 빌드 중..."
docker build -t "$IMAGE_NAME" .

if [ $? -eq 0 ]; then
    echo "이미지 빌드 성공: $IMAGE_NAME"
else
    echo "이미지 빌드 실패"
    exit 1
fi

echo "호스트 이미지 디렉토리 생성 중..."
mkdir -p "$HOST_IMAGE_PATH"
echo "이미지 저장 경로 생성 완료: $HOST_IMAGE_PATH"

echo "기존 컨테이너 중지 및 삭제 중..."
docker rm -f "$CONTAINER_NAME" 2>/dev/null && echo "$CONTAINER_NAME 정리 완료" || echo "종료할 컨테이너 없음"

echo "새 컨테이너 실행 중..."
docker run -d \
  -e SPRING_PROFILES_ACTIVE=dev \
  -e SERVER_PORT=8080 \
  -e BASE_URL=http://localhost:8080 \
  -p 8080:8080 \
  -v "$HOST_IMAGE_PATH":/app/uploads \
  --name "$CONTAINER_NAME" \
  "$IMAGE_NAME"

if [ $? -eq 0 ]; then
    echo "컨테이너 실행 성공: $CONTAINER_NAME"
    echo "애플리케이션 접속: http://localhost:8080"
    echo "이미지 저장 경로: $HOST_IMAGE_PATH"
    echo ""
    echo "유용한 명령어:"
    echo "  - 로그 확인: docker logs -f $CONTAINER_NAME"
    echo "  - 컨테이너 중지: docker stop $CONTAINER_NAME"
    echo "  - 컨테이너 재시작: docker restart $CONTAINER_NAME"
    echo "  - 이미지 폴더 확인: ls -la $HOST_IMAGE_PATH"
else
    echo "컨테이너 실행 실패"
    exit 1
fi