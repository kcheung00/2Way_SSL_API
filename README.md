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
https://localhost:9011/my_gateway/data
```