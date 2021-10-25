#
# JDK 8
#
FROM openjdk:11

ARG JAR_FILE

ADD target/mailer-1.0.0.jar /usr/share/sender/mailer-1.0.0.jar

CMD ["java", "-jar", "/usr/share/sender/mailer-1.0.0.jar"]