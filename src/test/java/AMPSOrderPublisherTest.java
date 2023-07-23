import org.junit.Test;

public class AMPSOrderPublisherTest {

    AMPSOrderPublisher ampsOrderPublisher;

    @Test
    public void pubTest() {
        ampsOrderPublisher = new AMPSOrderPublisher();

        ampsOrderPublisher.publish();
    }
}
