# Define a imagem base
FROM openjdk:11-jre-slim

WORKDIR /src/teste/servidores/
COPY . /src/teste/servidores/ 

CMD [ "java", "Main.java"]


# docker build -t mauriciosm1994/mauriciopbl
