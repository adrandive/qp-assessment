FROM openjdk:11
EXPOSE 8080
ADD target/Assessment-0.0.1-SNAPSHOT.jar Assessment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/Assessment-0.0.1-SNAPSHOT.jar