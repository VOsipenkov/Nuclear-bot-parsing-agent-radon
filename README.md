# Nuclear-bot-parsing-agent-radon
Parse site https://www.radon.ru/online-map/

# Build version: 1.3.0
docker login
./gradlew clean build -x test
# Build k8s
docker build -f Dockerfile-k8s -t vladi15151/nuclear-bot-parsing-agent-radon:1.3.0 .
# Build render
docker build -f Dockerfile-render -t vladi15151/nuclear-bot-parsing-agent-radon:1.3.0 .
docker push vladi15151/nuclear-bot-parsing-agent-radon:1.3.0