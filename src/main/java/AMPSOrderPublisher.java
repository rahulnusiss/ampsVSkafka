import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.exception.AMPSException;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public class AMPSOrderPublisher {
    Client c = new Client("publisher");

    public void publish () {
        try {
            c.connect("tcp://172.20.162.173:9007/amps/json");
            c.logon();


            OrderMessage.init();

            List<String> messageList = OrderMessage.messageList;
            long size = messageList.size();

            for  (long i = 0 ; i < size; ++ i) {
                // Publish
                c.publish("messages", messageList.get((int)i));

            }

        } catch (AMPSException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally
        {
            c.close();
        }
    }
}
