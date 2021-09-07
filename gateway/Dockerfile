FROM kcheung00/ubuntu_base:20.04
RUN addgroup --system gateway && adduser --system --group gateway
USER gateway:gateway
EXPOSE 9011
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]