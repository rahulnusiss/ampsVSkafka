import org.junit.Test;

public class KafkaOrderPublisherTest {

    KafkaOrderPublisher kafkaOrderPublisher;

    @Test
    public void kafkaPublisher() {

        kafkaOrderPublisher = new KafkaOrderPublisher();

        kafkaOrderPublisher.publish();

    }
}
