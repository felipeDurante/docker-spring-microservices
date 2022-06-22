FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /opt/app
COPY  ./fornecedor/target/fornecedor-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 8082
CMD ["java", "-jar", "/opt/app/fornecedor-0.0.1-SNAPSHOT.jar"]