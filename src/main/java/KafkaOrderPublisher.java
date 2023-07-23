import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.io.Serializable;
import java.util.Properties;

public class KafkaOrderPublisher {

    public void publish() {
        String topicName = "jsonKafka";

        // Set up Kafka producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.162.173:9092"); // Kafka broker(s) address
        //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker(s) address
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        // Create an instance of ObjectMapper to convert Java objects to JSON
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            // Send some JSON messages to the topic
            for (long i = 1; i <= 1000; i++) {
                // Create a simple example object (you can use your own custom objects here)
                ExampleObject obj = new ExampleObject("Record " + i, i * 100);

                // Serialize the object to JSON
                String jsonString = objectMapper.writeValueAsString(obj);

                // Send the JSON message to Kafka
                producer.send(new ProducerRecord<>(topicName, Long.toString(i), jsonString));
                System.out.println("Sent: " + jsonString);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
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
}
