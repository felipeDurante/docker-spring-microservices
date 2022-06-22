FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /opt/app
COPY  ./transportador/target/transportador-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 8083
CMD ["java", "-jar", "/opt/app/transportador-0.0.1-SNAPSHOT.jar"]