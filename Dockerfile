FROM adoptopenjdk/openjdk11:latest
COPY target/mn-sample-application-*.jar mn-sample-application.jar
EXPOSE 5000
CMD ["java", "-Dcom.sun.management.jmxremote", "-Xmx128m", "-jar", "mn-sample-application.jar"]