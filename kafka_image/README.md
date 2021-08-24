## Kafka Docker Image
- Version: Kafka 2.13-2.70 Release Dec 21, 2020
- Location: /opt/kafka_2.13-2.7.0
- Java Version: OpenJDK 1.8.x

## Zookeeper Docker Image
- Version: 3.4.13 Release July, 2018
- Location: /opt/zookeeper-3.4.13
- Java Version: OpenJDK 1.7.x

## Start the Kafka Container
Navigate to docker-compose.yml
```sh
$ docker-compose -f ./docker-compose.yml up -d
```