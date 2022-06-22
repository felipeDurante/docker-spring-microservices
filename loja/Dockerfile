FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /opt/app
COPY  ./loja/target/loja-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 8080
CMD ["java", "-jar", "/opt/app/loja-0.0.1-SNAPSHOT.jar"]