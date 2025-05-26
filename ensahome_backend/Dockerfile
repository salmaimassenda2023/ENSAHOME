# =============================================
# Étape 1: Build de l'application
# Cette étape utilise Maven pour compiler l'application Spring Boot
# =============================================
FROM maven:3.8.4-openjdk-17 AS build
# Utilise l'image Maven avec OpenJDK 17 comme base pour la compilation
# Le 'AS build' permet de référencer cette étape plus tard

WORKDIR /app
# Définit le répertoire de travail dans le conteneur

# Copier les fichiers de configuration Maven
COPY pom.xml .
# Copie le fichier pom.xml qui contient les dépendances Maven

COPY src ./src
# Copie le code source de l'application dans le répertoire src

# Build de l'application
RUN mvn clean package -DskipTests
# Exécute Maven pour compiler l'application
# - clean : nettoie les fichiers générés précédemment
# - package : compile et crée le fichier JAR
# -DskipTests : ignore les tests pour accélérer le build

# =============================================
# Étape 2: Création de l'image finale
# Cette étape crée une image légère pour l'exécution
# =============================================
FROM openjdk:17-jdk-slim
# Utilise une image JRE légère basée sur OpenJDK 17

# Variables d'environnement pour la configuration MongoDB
ENV MONGODB_CONFIG_SERVERS=config1:27021,config2:27022,config3:27023
# Définit les serveurs de configuration MongoDB avec leurs ports

ENV MONGODB_ROUTER_HOST=mongos1
# Définit l'hôte du routeur MongoDB (mongos)

ENV MONGODB_ROUTER_PORT=30333
# Définit le port du routeur MongoDB

ENV MONGODB_DATABASE=ensahome
# Définit le nom de la base de données MongoDB

# Créer un utilisateur non-root pour la sécurité
RUN useradd -m -s /bin/bash ensahome
# Crée un nouvel utilisateur 'ensahome' avec un répertoire home et un shell bash

USER ensahome
# Change l'utilisateur courant pour 'ensahome'

WORKDIR /home/ensahome
# Définit le répertoire de travail pour l'utilisateur ensahome

# Copier le JAR depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar
# Copie le fichier JAR généré depuis l'étape de build
# --from=build : référence l'étape de build précédente

# Exposer le port de l'application
EXPOSE 8080
# Indique que le conteneur écoutera sur le port 8080
# Note: Cette instruction est documentaire, il faut toujours mapper le port avec -p

# Commande pour démarrer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
# Définit la commande qui sera exécutée au démarrage du conteneur
# Lance l'application Spring Boot à partir du JAR 