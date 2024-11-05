REPOSITORY=/home/ubuntu/app

echo "> 현재 구동 중인 애플리케이션 pid 확인"

# 포트 8080을 점유 중인 모든 프로세스 종료
CURRENT_PID=$(lsof -t -i:8080)

if [ -n "$CURRENT_PID" ]; then
  echo "현재 포트 8080을 점유 중인 프로세스 종료: $CURRENT_PID"
  kill -9 $CURRENT_PID
  sleep 5
else
  echo "8080 포트를 점유 중인 프로세스가 없습니다."
fi

echo "> 새 애플리케이션 배포"

# 최신 JAR 파일 찾기
JAR_NAME=$(ls -tr $REPOSITORY/*SNAPSHOT.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행 (prod profile)"

# 새 애플리케이션 실행
nohup java -jar -Duser.timezone=Asia/Seoul $JAR_NAME >> $REPOSITORY/nohup.out 2>&1 &
