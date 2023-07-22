import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.Message;
import com.crankuptheamps.client.MessageStream;
import com.crankuptheamps.client.exception.AMPSException;

public class AMPSSubscriber {

    Client c = new Client("subscriber");

    public void subscribe () {
        try {
            c.connect("tcp://172.20.162.173:9007/amps/json");
            c.logon();


            // Subscribe
            MessageStream ms = c.subscribe("messages");

            int count = 0;

            while (ms.hasNext()) {
                if (count == 900) {
                    ms.close();
                    break;
                }
                Message m = ms.next();
                System.out.println("Received message: " + m.getData());

            }

        } catch (AMPSException e) {
            e.printStackTrace();
        }
        finally
        {
            c.close();
        }

    }
}
