# ampsVSkafka
comparison of performance between AMPS and KAFKA


1. Download Kafka:

Go to the Apache Kafka website (https://kafka.apache.org/downloads) and download the latest version of Kafka.

2. Extract the Kafka archive:

Extract the downloaded Kafka archive to a directory on your machine.

3. Start ZooKeeper:

Kafka uses ZooKeeper to manage cluster coordination. Before starting Kafka brokers, you need to start ZooKeeper. Open a terminal, navigate to your Kafka directory, and run the following command:
bash

bin/zookeeper-server-start.sh config/zookeeper.properties

4. Start Kafka Brokers:

Open a new terminal, navigate to your Kafka directory, and run the following command to start a Kafka broker:
bash

bin/kafka-server-start.sh config/server.properties

5. Create a Kafka Topic:

Now, you need to create a Kafka topic where your producer will send messages and your consumer will consume from. Open a new terminal and run the following command (replace your-topic-name with the desired topic name):
css

bin/kafka-topics.sh --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic your-topic-name

6. Run the Kafka Producer and Consumer:

With the Kafka cluster up and running, you can now compile and run your Kafka producer and consumer Java classes. Ensure that the producer is sending messages to the topic you created in the previous step.
