# ── Stage 1: Build ────────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /app

# Cache dependencies before copying source
COPY pom.xml .
RUN mvn dependency:go-offline -q

# Copy source and build the WAR
COPY src ./src
RUN mvn clean package -DskipTests -q

# ── Stage 2: Runtime ───────────────────────────────────────────
FROM tomcat:11-jre17

# Remove default ROOT app
RUN rm -rf /usr/local/tomcat/webapps/ROOT

# Deploy WAR as ROOT so the app is served at /
COPY --from=build /app/target/nes-academy-class27.war \
     /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080

CMD ["catalina.sh", "run"]
