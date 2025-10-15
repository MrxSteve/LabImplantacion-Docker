# Usamos imagen base con JDK 17
FROM eclipse-temurin:17-jdk-alpine

# Establecemos el directorio de trabajo
WORKDIR /app

# Copiamos el pom y descargamos dependencias primero (mejor cache)
COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:go-offline

# Copiamos el resto del código y lo construimos
COPY . .
RUN ./mvnw clean package -DskipTests

# Ejecutamos el JAR generado
CMD ["java", "-jar", "target/backend-0.0.1-SNAPSHOT.jar"]
