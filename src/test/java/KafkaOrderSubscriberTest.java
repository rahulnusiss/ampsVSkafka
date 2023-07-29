import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.*;
import org.junit.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class KafkaOrderSubscriberTest {

    List<String> messageList = new ArrayList<>();

    private final static String TEST_MESSAGE = "No of messages in this second : ";

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
            Duration duration = Duration.ofNanos(1);

            long i = 0;

            int count = 0;
            int prevCount = 0;

            long prevTime = 0;
            long currTime = System.currentTimeMillis();

            // Start consuming messages
            while (true) {
                long runningTime = System.currentTimeMillis();
                if ( runningTime - currTime > TimeCheck.RATE_TIME_WINDOW) {
                    prevTime = currTime;
                    currTime = runningTime;
                    System.out.println(TEST_MESSAGE + count);
                    count = 0;
                }
                ConsumerRecords<String, String> records = consumer.poll(duration);

                for (ConsumerRecord<String, String> record : records) {
                    String value = record.value();
                    i++;
                    count ++;
                }
                if (OrderMessage.NUM_MESSAGES == i ) {
                    break;
                }
            }

//            for (String msgJson : messageList) {
//                System.out.print("Subscribed: " + msgJson);
//            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            consumer.close();
        }
    }
}
