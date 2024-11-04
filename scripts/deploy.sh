REPOSITORY=/home/ubuntu/app

echo "> 현재 구동 중인 애플리케이션 pid 확인"

# 현재 실행 중인 애플리케이션의 PID 확인
CURRENT_PID=$(pgrep -fla java | grep hayan | awk '{print $1}')

echo "현재 구동 중인 애플리케이션 pid: $CURRENT_PID"

# 현재 구동 중인 애플리케이션이 있으면 종료
if [ -n "$CURRENT_PID" ]; then
  echo "> kill -15 $CURRENT_PID"
  kill -15 $CURRENT_PID

  # 프로세스가 완전히 종료될 때까지 대기
  for i in {1..10}; do
    if ps -p $CURRENT_PID > /dev/null; then
      echo "기존 애플리케이션 종료 중... 잠시 대기합니다."
      sleep 2
    else
      echo "기존 애플리케이션이 정상적으로 종료되었습니다."
      break
    fi

    # 만약 10번의 반복 내에 종료되지 않으면 강제 종료 시도
    if [ $i -eq 10 ]; then
      echo "기존 애플리케이션이 종료되지 않아 강제 종료합니다."
      kill -9 $CURRENT_PID
    fi
  done
else
  echo "현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다."
fi

echo "> 새 애플리케이션 배포"

# 최신 JAR 파일 찾기
JAR_NAME=$(ls -tr $REPOSITORY/*SNAPSHOT.jar | tail -n 1)

echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"

chmod +x $JAR_NAME

echo "> $JAR_NAME 실행 (prod profile)"

# 새 애플리케이션 실행
nohup java -jar -Dspring.profiles.active=prod -Duser.timezone=Asia/Seoul $JAR_NAME >> $REPOSITORY/nohup.out 2>&1 &
