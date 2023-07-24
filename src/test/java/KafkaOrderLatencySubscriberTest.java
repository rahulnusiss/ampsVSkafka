import com.crankuptheamps.client.Message;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.*;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaOrderLatencySubscriberTest {

    List<String> messageList = new ArrayList<>();

    @Test
    public void kafkaSubscriber() {
        String topicName = KafkaOrderPublisher.KAFKA_TOPIC_NAME;

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

            long sum = 0;
            int count = 0;


            // Start consuming messages
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(duration);
                for (ConsumerRecord<String, String> record : records) {
                    String value = record.value();
                    OrderMessage orderMessage = objectMapper.readValue(value, OrderMessage.class);
                    long currTime = System.currentTimeMillis();
                    long msgTime = orderMessage.getTimestamp();
                    sum += (currTime-msgTime);
                    count ++;
                }
                if (OrderMessage.NUM_MESSAGES == count ) {
                    break;
                }
            }

            double avgLatency = (sum/OrderMessage.NUM_MESSAGES);
            System.out.println("Kafka Avg latency : " + avgLatency);


        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
