FROM openjdk:19-jdk-alpine

RUN apk update && apk add bash curl netcat-openbsd

COPY ./docker/docker-entrypoint.sh /usr/bin/docker-entrypoint.sh
RUN chmod +x /usr/bin/docker-entrypoint.sh

ENV JAVA_OPTS=""
ENV MAX_RAM_PERCENTAGE=70

COPY ./build/libs/backend-0.0.1-SNAPSHOT.jar backend.jar

ENTRYPOINT ["docker-entrypoint.sh"]
