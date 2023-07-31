FROM openjdk:17
COPY /build/libs/parsing.agent.radon-0.0.1-SNAPSHOT.jar /parsing-agent-radon.jar
EXPOSE 80
#RUN microdnf update
#RUN microdnf install -y gcc
#RUN microdnf install -y curl
ENTRYPOINT java $JAVA_OPTS -jar parsing-agent-radon.jar