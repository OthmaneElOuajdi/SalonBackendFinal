# Dockerfile pour déploiement sur Render
FROM openjdk:21-jdk-slim

# Définir le répertoire de travail
WORKDIR /app

# Copier le fichier pom.xml et le wrapper Maven
COPY pom.xml .
COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .

# Rendre le wrapper Maven exécutable
RUN chmod +x mvnw

# Télécharger les dépendances (cache Docker)
RUN ./mvnw dependency:go-offline -B

# Copier le code source
COPY src src

# Construire l'application
RUN ./mvnw clean package -DskipTests

# Exposer le port
EXPOSE $PORT

# Commande de démarrage
CMD ["java", "-Dserver.port=${PORT}", "-jar", "target/coiffure-0.0.1-SNAPSHOT.jar"]
