# ETAPA DE CONSTRUCCIÓN (BUILD STAGE)
# Usamos una imagen que tiene Maven y JDK 21 para compilar el proyecto.
FROM maven:3.9.6-eclipse-temurin-21-alpine AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# ETAPA DE EJECUCIÓN (RUNTIME STAGE)
# Usamos solo el JRE 21 (más ligero) para ejecutar la aplicación.
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]