# Bazowy obraz Eclipse Temurin (JDK 17)
FROM eclipse-temurin:17-jdk

# Ustaw katalog roboczy w kontenerze
WORKDIR /app

# Kopiowanie pliku JAR do kontenera
COPY target/material-management-0.0.1-SNAPSHOT.jar app.jar



# Ustawienie zmiennej środowiskowej (opcjonalne)
ENV SPRING_PROFILES_ACTIVE=prod

# Komenda do uruchomienia aplikacji na porcie 8083
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8083"]
