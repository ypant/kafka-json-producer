# kafka-json-producer

Demonstrates how to generate Kafka events in JSON a) using log4j b) native Kafka Producer on a *NIX/MacOS platform.


## Pre-requisites

* Java, maven, git client have already been installed

## Kafka Installation

* Download kafka (for this project I have downloaded kafka_2.10-0.8.2.2)
* Untar the downloaded file: this projects assumes `/opt` directory: `cd /opt; tar xzvf ~/Downloads/kafka_2.10-0.8.2.2.tgz`
* Create a symbolic link in opt directory: `ln -sf kafka_2.10-0.8.2.2 kafka`
* Create appropriate aliases for starting kafka components. Here are some of my aliases (default from kafka project wiki):
````
    alias start_zookeeper='/opt/kafka/bin/zookeeper-server-start.sh /opt/kafka/config/zookeeper.properties'
    alias start_broker='/opt/kafka/bin/kafka-server-start.sh  /opt/kafka/config/server.properties'
    alias create_topic='/opt/kafka/bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kafkatopic'
    alias list_topic='/opt/kafka/bin/kafka-topics.sh --list --zookeeper localhost:2181 kafkatopic'
    alias start_producer='/opt/kafka/bin/kafka-console-producer.sh --broker-list localhost:9092 --topic kafkatopic'
    alias start_consumer='/opt/kafka/bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic kafkatopic --from-beginning'
````
## Usage 

* Download this project to  directory as appropriate:
````
    % git clone https://github.com/ypant/kafka-json-producer.git
````
* Maven project file structure: 
````
    .
    ./LICENSE
    ./pom.xml
    ./README.md
    ./src
    ./src/main
    ./src/main/java
    ./src/main/java/com
    ./src/main/java/com/example
    ./src/main/java/com/example/JsonProducer.java
    ./src/main/resources
    ./src/main/resources/log4j.xml
````
* Start Kafka components in separate terminals:
````
    % start_zookeeper
    % start_broker
    % create_topic and list_topic - to verify the topic has been created
    % start_consumer
    % start_producer: Make sure that the messages sent from the producer window appears in consumer window
````    
* Compile and execute the program using maven (from project root directory): 
````
    % mvn clean compile
    % mvn exec:java -Dexec.mainClass="com.example.JsonProducer"
````    

## History

* 15 Nov 2015 - Initial Revision


## License
[MIT License](LICENSE)

