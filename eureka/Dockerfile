FROM adoptopenjdk/openjdk11:alpine
RUN mkdir /opt/app
COPY  ./eureka/target/eureka-0.0.1-SNAPSHOT.jar /opt/app
EXPOSE 8761
CMD ["java", "-jar", "/opt/app/eureka-0.0.1-SNAPSHOT.jar"]