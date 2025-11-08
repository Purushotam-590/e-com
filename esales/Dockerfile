RUN ./mvnw package -DskipTests

FROM openjdk:17-jre-slim
WORKDIR /esales
COPY --from=builder /esales/target/*.jar esales.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "esales.jar"]