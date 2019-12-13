FROM java:8
VOLUME /tmp
ADD security-service-1.0-SNAPSHOT.jar security-service.jar
ENTRYPOINT ["java","-Xms200m","-Xmx300m","-jar","/security-service.jar"]