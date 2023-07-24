import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.exception.AMPSException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

public class AMPSOrderPublisher {
    public final static String AMPS_TOPIC = "messages";
    Client c = new Client("publisher");

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public void publish () {
        try {
            c.connect("tcp://172.20.162.173:9007/amps/json");
            //c.connect("tcp://172.20.162.173:9007/amps/binary");
            c.logon();

            OrderMessage.init();

            List<String> messageList = OrderMessage.messageList;
            List<OrderMessage> orderMessageList = OrderMessage.orderMessageList;

            throughputPublish(messageList);
            //latencyPublish(orderMessageList);
            //binaryPublish(messageList);


        } catch (AMPSException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } finally
        {
            c.close();
        }
    }

    private void throughputPublish(List<String> messageList) throws AMPSException {
        long size = messageList.size();
        for  (long i = 0 ; i < size; ++ i) {
            // Publish json
            c.publish(AMPS_TOPIC, messageList.get((int)i));

        }
    }

    private void latencyPublish(List<OrderMessage> orderMessageList) throws AMPSException, JsonProcessingException {
        long size = orderMessageList.size();
        for  (long i = 0 ; i < size; ++ i) {
            OrderMessage orderMessage = orderMessageList.get((int) i);
            orderMessage.setTimestamp(System.currentTimeMillis());
            String jsonString = objectMapper.writeValueAsString(orderMessage);
            // Publish json
            c.publish(AMPS_TOPIC, jsonString);

        }

    }

    private void binaryPublish(List<String> messageList) throws AMPSException {
        byte[] topic = AMPS_TOPIC.getBytes();
        int topicL = AMPS_TOPIC.length();
        long size = messageList.size();
        for  (long i = 0 ; i < size; ++ i) {
            // Publish binary
            String orderStr = messageList.get((int) i);
            byte[] bytesData = orderStr.getBytes();
            int byteDataLength = bytesData.length;

            c.publish(topic, 0,topicL,bytesData,0,byteDataLength);

        }
    }


}
