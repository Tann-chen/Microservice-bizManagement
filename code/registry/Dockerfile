FROM java:8-jre

VOLUME /tmp
ADD ./target/registry-0.0.1-SNAPSHOT.jar app.jar
CMD ["java", "-Xmx300m", "-jar", "app.jar"]

EXPOSE 8761