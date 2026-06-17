FROM eclipse-temurin:8-jdk
ADD greeting-server.jar greeting-server.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","greeting-server.jar"]
