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

public class AMPSOrderSubscriberTest {

    List<String> messageList = new ArrayList<>();
    ObjectMapper objectMapper = new ObjectMapper();

    Client c = new Client("subscriber");

    @Test
    public void subscribe () {
        try {
            c.connect("tcp://172.20.162.173:9007/amps/json");
            c.logon();


            // Subscribe
            MessageStream ms = c.subscribe("messages");

            int count = 0;

            while (ms.hasNext()) {
                Message m = ms.next();
                String data = m.getData();
                messageList.add(data);
                System.out.println("Sub : " + data);
            }

            for (String msgJson : messageList) {
                System.out.println("Subscribed : " + msgJson);
            }


        } catch (AMPSException e) {
            e.printStackTrace();
        }  finally
        {
            c.close();
        }

    }


}
