FROM adoptopenjdk/openjdk11:alpine
WORKDIR /opt/app
# RUN mkdir app
RUN mkdir -m777 -p ../microservice-repo/fornecedor
RUN mkdir -m777 ../microservice-repo/loja
RUN mkdir -m777 ../microservice-repo/transportador
COPY  /config-server/target/config-server-0.0.1-SNAPSHOT.jar .
COPY ../microservice-repo/fornecedor/* ../microservice-repo/fornecedor/
COPY ../microservice-repo/loja/* ../microservice-repo/loja/
COPY ../microservice-repo/transportador/* ../microservice-repo/transportador/
EXPOSE 8888
CMD ["java", "-jar", "/opt/app/config-server-0.0.1-SNAPSHOT.jar"]