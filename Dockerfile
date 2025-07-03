# Etapa 1: Build do projeto com Maven e Java
FROM maven:3.9.6-eclipse-temurin-17 AS build

# Instala o Git (necessário para clonar o repositório)
USER root
RUN apt-get update && apt-get install -y git

# Clona o projeto do GitHub
RUN git clone https://github.com/JoaoAlisson1/Sistema-de-Manuten-o.git /opt/app

# Define o diretório de trabalho
WORKDIR /opt/app

# Compila o projeto
RUN mvn clean package -DskipTests


# Etapa 2: Imagem final com WildFly
FROM jboss/wildfly:latest

# Copia o WAR gerado para o WildFly
COPY --from=build /opt/app/target/SistemaManutencao.war /opt/jboss/wildfly/standalone/deployments/


# Volume para logs
VOLUME ["/opt/jboss/wildfly/standalone/log"]

# Exposição da porta
EXPOSE 8080

# Force correct encoding
ENV LANG en_US.UTF-8
ENV LC_ALL en_US.UTF-8
ENV JAVA_TOOL_OPTIONS="-Dfile.encoding=UTF-8"

# Inicia o WildFly
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0"]
