FROM eclipse-temurin:21-jre-alpine

WORKDIR /usr/src/pizzeria

COPY build/libs/pizzeria-webflux.jar pizzeria-webflux.jar

CMD java $JAVA_OPTS -jar pizzeria-webflux.jar