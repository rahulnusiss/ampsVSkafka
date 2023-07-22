import com.crankuptheamps.client.Client;
import com.crankuptheamps.client.exception.AMPSException;

public class AMPSPublisher {

    Client c = new Client("publisher");

    public void publish () {
        try {
            c.connect("tcp://172.20.162.173:9007/amps/json");
            c.logon();

            int count = 0;

            while (true) {
                if (count == 10000) {
                    break;
                }
                // Publish
                c.publish("messages", "{ \"message\" : \"Hello, world!\" }");
                count++;
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
