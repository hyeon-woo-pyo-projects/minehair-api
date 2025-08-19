#!/bin/bash
# 컨테이너 이름
CONTAINER_NAME="minehair-mariadb"
# MariaDB 버전
MARIADB_VERSION="10.6"
# 데이터 경로 (호스트)
DATA_PATH="/root/minehair/mariadb/data"
# 비밀번호
MARIADB_ROOT_PASSWORD="minehair401"
# 네트워크 이름
NETWORK_NAME="minehair-network"

# 네트워크 생성 (이미 있으면 에러 무시)
docker network create $NETWORK_NAME 2>/dev/null && echo "네트워크 $NETWORK_NAME 생성" || echo "네트워크 $NETWORK_NAME 이미 존재"

# 컨테이너 존재 여부 확인
if [ "$(docker ps -aq -f name=$CONTAINER_NAME)" ]; then
    echo "⚠️  컨테이너 $CONTAINER_NAME 이미 존재합니다."
    echo "👉 중지 및 삭제 후 다시 실행합니다."
    docker stop $CONTAINER_NAME
    docker rm $CONTAINER_NAME
fi

# 실행
docker run -d \
  --name $CONTAINER_NAME \
  --network $NETWORK_NAME \
  -p 3306:3306 \
  -e MARIADB_ROOT_PASSWORD=$MARIADB_ROOT_PASSWORD \
  -v $DATA_PATH:/var/lib/mysql \
  --restart=always \
  mariadb:$MARIADB_VERSION

echo "✅ MariaDB 컨테이너 $CONTAINER_NAME 실행 완료!"