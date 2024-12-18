# Bazowy obraz Eclipse Temurin (JDK 17)
FROM eclipse-temurin:17-jdk

# Ustaw katalog roboczy w kontenerze
WORKDIR /app

# Kopiowanie pliku JAR do kontenera
COPY target/project-management-0.0.1-SNAPSHOT.jar app.jar

# Ustawienie portu, na którym działa Spring Boot
EXPOSE 8081

# Ustawienie zmiennej środowiskowej (opcjonalne)
ENV SPRING_PROFILES_ACTIVE=prod

# Komenda do uruchomienia aplikacji na porcie 8081
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8081"]
