FROM adoptopenjdk/openjdk11:latest
RUN mkdir /opt/app
COPY target/assignment-SNAPSHOT.jar /opt/app
CMD ["java", "-jar", "/opt/app/assignment-SNAPSHOT.jar"]