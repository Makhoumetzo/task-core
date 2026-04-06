# ══════════════════════════════════════════════════════════════════════════════
# Dockerfile pour task-core (Spring Boot API REST)
# Utilise un multi-stage build pour réduire la taille de l'image finale
# ══════════════════════════════════════════════════════════════════════════════

# ── Stage 1 : Build ──────────────────────────────────────────────────────────
# Image Maven + JDK 21 pour compiler le projet et créer le JAR
FROM maven:3.9-eclipse-temurin-21 AS build

# Créer le dossier de travail dans le conteneur
WORKDIR /app

# Copier d'abord uniquement le pom.xml pour profiter du cache Docker
# Si le pom.xml n'a pas changé, Docker réutilise les dépendances en cache
COPY pom.xml .

# Télécharger toutes les dépendances Maven sans compiler (-B = mode batch)
RUN mvn dependency:go-offline -B

# Copier le code source (après les dépendances, pour optimiser le cache)
COPY src ./src

# Compiler le projet et créer le JAR exécutable
# -DskipTests : les tests sont déjà exécutés dans le pipeline CI
RUN mvn clean package -DskipTests -B

# ── Stage 2 : Runtime ────────────────────────────────────────────────────────
# Image légère avec seulement le JRE 21 (pas le JDK complet)
FROM eclipse-temurin:21-jre

# Dossier de travail
WORKDIR /app

# Copier UNIQUEMENT le JAR depuis le stage "build"
# Tout le reste (Maven, code source, dépendances de build) est jeté
COPY --from=build /app/target/*.jar app.jar

# Documenter le port exposé par l'application (port 8085 pour le Groupe 1)
EXPOSE 8085

# Commande de démarrage du conteneur
# java -jar app.jar lance l'application Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]
