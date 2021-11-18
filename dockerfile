FROM adoptopenjdk/openjdk11:alpine-jre
VOLUME /tmp
COPY ./build/libs/* app.jar
ENTRYPOINT ["java","-jar","/app.jar"]