FROM java:8
VOLUME /tmp


#WORKDIR /application/jenkins/deploy/src/main/docker
#RUN pwd

ADD security-service-1.0-SNAPSHOT.jar security-service.jar
ENTRYPOINT ["java","-Xms200m","-Xmx300m","-jar","/security-service.jar"]