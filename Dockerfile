FROM java:alpine
EXPOSE 8080
COPY wander-notes-0.0.1.jar /usr/local/
WORKDIR /tmp
CMD ["java", "-jar", "/usr/local/wander-notes-0.0.1.jar"]