# Primera etapa: Construcción de la aplicación
FROM eclipse-temurin:21-jdk AS builder

WORKDIR /app

# Copia todos los archivos del proyecto al contenedor
COPY . /app

# Ejecuta el build de Maven
RUN ./mvnw clean package -DskipTests

# Segunda etapa: Creación de la imagen final
FROM eclipse-temurin:21-jre

WORKDIR /app

# Copia el archivo JAR desde la etapa de construcción
COPY --from=builder /app/target/soccer-0.0.1-SNAPSHOT.jar /app/app.jar

EXPOSE 8080

CMD ["java", "-jar", "/app/app.jar"]