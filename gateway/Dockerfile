FROM kcheung00/ubuntu_base:20.04
RUN addgroup --system gateway && adduser --system --group gateway
USER gateway:gateway
EXPOSE 9011

RUN mkdir -p /apps

COPY ../SSL_FILES/my_api* /apps

# Import Client Public Cert File to Services JKS File
RUN keytool -import -storepass my_api_services -noprompt -alias my_api_gateway -keystore /apps/my_api_services.jks -file /apps/my_api_gateway.crt

# Import Server Public Cert File to Gateway JKS File
RUN keytool -import -storepass my_api_gateway -noprompt -alias my_api_services -keystore /apps/my_api_gateway.jks -file /apps/my_api_services.crt

ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} /apps/app.jar
ENTRYPOINT ["java","-jar","/apps/app.jar"]
