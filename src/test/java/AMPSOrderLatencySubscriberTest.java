import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.crankuptheamps.client.exception.AMPSException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class AMPSOrderLatencySubscriberTest {

    List<String> messageList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    Client c = new Client("subscriber");

    @Test
    public void subscribe () {
        try {
            c.connect("tcp://172.20.162.173:9007/amps/json");
            //c.connect("tcp://172.20.162.173:9007/amps/binary");
            c.logon();

            // Subscribe
            MessageStream ms = c.subscribe(AMPSOrderPublisher.AMPS_TOPIC);
            long sum = 0;
            int count = 0;

            while (ms.hasNext()) {
                Message m = ms.next();
                OrderMessage orderMessage = objectMapper.readValue(m.getData(), OrderMessage.class);
                long currTime = System.currentTimeMillis();
                long msgTime = orderMessage.getTimestamp();
                sum += (currTime-msgTime);
                count ++;
                if (count == OrderMessage.NUM_MESSAGES) {
                    break;
                }
            }

            double avgLatency = (sum/OrderMessage.NUM_MESSAGES);
            System.out.println("AMPS Avg latency : " + avgLatency);

        } catch (AMPSException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally
        {
            c.close();
        }

    }


}
