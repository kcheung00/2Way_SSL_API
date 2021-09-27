# Two Way SSL API Microservices Demo
## Quick Start
### Assumption: 
Certificates were created and imported to corresponding components. Detail refer to SSL-Spring-API.pdf

### Start Gateway (client) service
1. Clone from git repo
   ```
   git clone https://github.com/kcheung00/2Way_SSL_API.git
   ```
2. Navigate to gateway (client) folder
   ```
   cd 2Way_SSL_API/gateway
   ```
3. Build the project
   ```
   mvn clean install
   ```
4. Execute the project
   ```
   mvn spring-boot:run
   ```

### Start API microservice 1 (ms1)
1. Navigate to m1 folder
   ```
   cd 2Way_SSL_API/ms1
   ```
2. Build the project
   ```
   mvn clean install
   ```
3. Execute the project
   ```
   mvn spring-boot:run
   ```

### Start API microservice 2 (ms2)
1. Navigate to m1 folder
   ```
   cd 2Way_SSL_API/ms2
   ```
2. Build the project
   ```
   mvn clean install
   ```
3. Execute the project
   ```
   mvn spring-boot:run
   ```

### Quick Check
Browser Test
```
https://localhost:9011/my_gateway/ms1data
https://localhost:9011/my_gateway/ms2data
https://localhost:9011/my_gateway/greeting
https://localhost:9011/my_gateway/data
```
CURL Test (Refer to postman_collection.json)
```sh
curl --cert my_api_services.crt --key my_api_services.key --location --request POST 'https://localhost:9011/my_gateway/ms2sendmsg' --header 'Content-Type: text/plain' --data-raw 'Test Message through gateway to MS 2 method'

curl --cert my_api_services.crt --key my_api_services.key --location --request POST 'https://localhost:9011/my_gateway/gw_post_msg' --header 'Content-Type: text/plain' --data-raw 'Test Message for gateway'
```

Quick Note:
```
Gateway - 9011 (80:9011)
MS1 - 9022 (9011:9022)
MS2 - 9033 (9011:9033)
-p 8080:80 (80 is inside container, 8080 is inside docker host)
```
