FROM java:8-jre

VOLUME /tmp
ADD ./target/gateway-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-Xmx200m", "-jar", "app.jar"]

EXPOSE 8080