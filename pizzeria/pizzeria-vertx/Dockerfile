FROM eclipse-temurin:25-jre-alpine

WORKDIR /usr/src/pizzeria

COPY build/libs/pizzeria-vertx.jar pizzeria-vertx.jar

CMD java $JAVA_OPTS -jar pizzeria-vertx.jar