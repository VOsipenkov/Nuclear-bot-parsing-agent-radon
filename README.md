# Nuclear-bot-parsing-agent-radon
Parse site https://www.radon.ru/online-map/

./gradlew clean build -x test
docker login
docker build -t vladi15151/nuclear-bot-parsing-agent-radon:1.1.4 .
docker push vladi15151/nuclear-bot-parsing-agent-radon:1.1.4