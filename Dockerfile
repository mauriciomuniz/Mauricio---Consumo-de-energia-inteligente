# Define a imagem base
FROM openjdk:11-jre-slim

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia os arquivos da aplicação para o contêiner
COPY pbl1.1/ /app

# Define o comando para compilar e empacotar a aplicação
RUN javac Main.java && jar cfe app.jar Main Main.class

# Expõe as portas necessárias para a aplicação
EXPOSE 7776
EXPOSE 8081

# Define o comando para iniciar a aplicação
CMD ["java", "-Dserver.port=7776", "-Dmanagement.server.port=8081", "-jar", "app.jar"]
