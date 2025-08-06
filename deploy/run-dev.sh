#!/bin/bash

# 설정 변수
IMAGE_NAME="minehair-dev"
CONTAINER_NAME="minehair-dev"

echo "Docker 이미지 빌드 중..."
docker build -t "$IMAGE_NAME" .

if [ $? -eq 0 ]; then
    echo "이미지 빌드 성공: $IMAGE_NAME"
else
    echo "이미지 빌드 실패"
    exit 1
fi

echo "기존 컨테이너 중지 및 삭제 중..."
docker rm -f "$CONTAINER_NAME" 2>/dev/null && echo "$CONTAINER_NAME 정리 완료" || echo "종료할 컨테이너 없음"

echo "새 컨테이너 실행 중..."
docker run -d \
  -e SPRING_PROFILES_ACTIVE=dev \
  -e SERVER_PORT=8081 \
  -p 8081:8081 \
  --name "$CONTAINER_NAME" \
  "$IMAGE_NAME"

if [ $? -eq 0 ]; then
    echo "컨테이너 실행 성공: $CONTAINER_NAME"
    echo "애플리케이션 접속: http://localhost:8081"
    echo ""
    echo "유용한 명령어:"
    echo "  - 로그 확인: docker logs $CONTAINER_NAME"
    echo "  - 컨테이너 중지: docker stop $CONTAINER_NAME"
    echo "  - 컨테이너 재시작: docker restart $CONTAINER_NAME"
else
    echo "컨테이너 실행 실패"
    exit 1
fi
