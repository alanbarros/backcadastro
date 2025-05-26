# Use uma imagem base do OpenJDK para Java 17
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o arquivo pom.xml para que as dependências possam ser baixadas
COPY pom.xml .

# Copia o código fonte da aplicação
COPY src ./src

# Empacota a aplicação Spring Boot em um JAR executável
# O comando 'mvn clean package -DskipTests' limpa, empacota e pula os testes
RUN ./mvnw clean package -DskipTests

# Expõe a porta que a aplicação Spring Boot usa (padrão é 8080)
EXPOSE 8080

# Define o comando para executar a aplicação JAR
# O nome do JAR será backcadastro-0.0.1-SNAPSHOT.jar com base no pom.xml
ENTRYPOINT ["java", "-jar", "target/backcadastro-0.0.1-SNAPSHOT.jar"]
