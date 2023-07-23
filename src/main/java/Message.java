import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Message {

    public static final long NUM_WARMUP_MESSAGES = 10000;
    public static final long NUM_MESSAGES = 1000000;
    private String orderId;
    private String price;
    private String side;
    private String currency;
    private String symbol;

    public static final  List<String> messageList = new ArrayList<String>();
    public static final List<String> warmupMessageList = new ArrayList<String>();

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public Message(String orderId, String price, String side, String currency, String symbol) {
        this.orderId = orderId;
        this.price = price;
        this.side = side;
        this.currency = currency;
        this.symbol = symbol;
    }

    public static void init() throws JsonProcessingException {
        String s1 = "RELI";
        String s2 = "INFY";
        String s3 = "TCS";

        String p1 = "11.1";
        String p2 = "12.1";
        String p3 = "13.1";

        String sd1 = "BUY";
        String sd2 = "SELL";
        String sd3 = "SHORT_SELL";

        String c1 = "INR";
        String c2 = "TWD";
        String c3 = "HKD";

        Random rand = new Random();


        for (long i = 1; i <= NUM_MESSAGES; ++i) {
            int rz = rand.nextInt(3) + 1;
            Message msg = null;
            switch (rz) {
                case 1:
                    msg = new Message(String.valueOf(i),p1,sd1,c1,s1 );
                    break;
                case 2:
                    msg = new Message(String.valueOf(i),p2,sd2,c2,s2 );
                    break;
                case 3:
                    msg = new Message(String.valueOf(i),p3,sd3,c3,s3 );
                    break;
                default:
                    break;
            }
            String jsonString = objectMapper.writeValueAsString(msg);
            messageList.add(jsonString);
            if ( i <= NUM_WARMUP_MESSAGES ) {
                warmupMessageList.add(jsonString);
            }
        }

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSide() {
        return side;
    }

    public void setSide(String side) {
        this.side = side;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
