import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.*;
import org.junit.Test;

import java.io.Serializable;
import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class KakaSubscriberTest {

    @Test
    public void kafkaSubscriber() {
        String topicName = "jsonKafka";

        // Set up Kafka consumer properties
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.162.173:9092"); // Kafka broker(s) address
        //props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker(s) address
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        //props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group"); // Consumer group ID
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "test-consumer-group"); // Consumer group ID


        Consumer<String, String> consumer = new KafkaConsumer<>(props);

        // Create an instance of ObjectMapper to convert JSON to Java objects
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Subscribe to the topic
            consumer.subscribe(Arrays.asList(topicName));
            Duration duration = Duration.ofMillis(100);

            // Start consuming messages
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(duration);
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("Received: key=" + record.key() + ", value=" + record.value());

                    // Deserialize the JSON message to a Java object
                    KafkaSubscriber.ExampleObject obj = objectMapper.readValue(record.value(), KafkaSubscriber.ExampleObject.class);

                    // Use the Java object as needed
                    System.out.println("Deserialized Object: " + obj);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    // Example custom object
    public static class ExampleObject implements Serializable {
        private String name;
        private long value;

        public ExampleObject() {
        }

        public ExampleObject(String name, long value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

    }

    @Test
    public void randtest( ) {
        Random rand = new Random();
        for (int i = 0 ; i < 100 ; ++i ) {
            int i1 = rand.nextInt(3);
            System.out.println(i1);
        }
    }


}
