# =============================================
# Étape 1: Build de l'application
# Cette étape utilise Maven pour compiler l'application Spring Boot
# =============================================
FROM maven:3.8.4-openjdk-17 AS build

# Définit le répertoire de travail dans le conteneur
WORKDIR /app

# Copier le pom.xml et télécharger les dépendances
COPY pom.xml .
RUN mvn dependency:go-offline

# Copier le code source
COPY src ./src

# Package l'application
RUN mvn package -DskipTests

# =============================================
# Étape 2: Création de l'image finale
# Cette étape crée une image légère pour l'exécution
# =============================================
FROM openjdk:17-jdk-slim

# Variables d'environnement pour la configuration MongoDB
ENV MONGODB_CONFIG_SERVERS=config1:27021,config2:27022,config3:27023
ENV MONGODB_ROUTER_HOST=mongos1
ENV MONGODB_ROUTER_PORT=30333
ENV MONGODB_DATABASE=ensahome

# Créer un utilisateur non-root pour la sécurité
RUN useradd -m -s /bin/bash ensahome

# Définit le répertoire de travail pour l'utilisateur ensahome
WORKDIR /app

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Changer le propriétaire du fichier JAR
RUN chown ensahome:ensahome app.jar

# Change l'utilisateur courant pour 'ensahome'
USER ensahome

# Exposer le port de l'application
EXPOSE 8080

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]