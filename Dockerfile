FROM openjdk:15
COPY /build/libs/parsing.agent.radon-0.0.1-SNAPSHOT.jar /parsing-agent-radon.jar
EXPOSE 80
ENTRYPOINT ["java", "-jar", "parsing-agent-radon.jar"]