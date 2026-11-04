FROM eclipse-temurin:25-jre-alpine

WORKDIR /usr/src/osm

COPY build/libs/osm-boot.jar osm-boot.jar

CMD java $JAVA_OPTS -jar osm-boot.jar