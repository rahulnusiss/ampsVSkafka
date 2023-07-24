import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

public class KafkaOrderPublisher {

    public static final ObjectMapper objectMapper = new ObjectMapper();
    public static final String KAFKA_TOPIC_NAME = "jsonKafka";

    Producer<String, String> producer;

    public void publish() {


        // Set up Kafka producer properties
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "172.20.162.173:9092"); // Kafka broker(s) address wsl
        //props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092"); // Kafka broker(s) address windows
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);

        try {
            OrderMessage.init();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<String> messageList = OrderMessage.messageList;
        List<OrderMessage> orderMessageList = OrderMessage.orderMessageList;


        try {
            throughputPublish(messageList);
            //latencyPublish(orderMessageList);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            producer.close();
        }
    }

    private void throughputPublish(List<String> messageList) {
        long size = messageList.size();
        // Send some JSON messages to the topic
        for (long i = 0; i < size; i++) {

            String msg = messageList.get((int) i);
            // Send the JSON message to Kafka
            producer.send(new ProducerRecord<>(KAFKA_TOPIC_NAME, Long.toString(i), msg));
            //System.out.println("Sent: " + msg);
        }
    }

    private void latencyPublish(List<OrderMessage> orderMessageList) throws JsonProcessingException {
        long size = orderMessageList.size();
        for  (long i = 0 ; i < size; ++ i) {
            OrderMessage orderMessage = orderMessageList.get((int) i);
            orderMessage.setTimestamp(System.currentTimeMillis());
            String jsonString = objectMapper.writeValueAsString(orderMessage);
            // Publish json
            producer.send(new ProducerRecord<>(KAFKA_TOPIC_NAME, Long.toString(i), jsonString));

        }

    }

}
