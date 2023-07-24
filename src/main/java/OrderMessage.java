import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderMessage implements Serializable {

    public static final long NUM_MESSAGES = 5000000;
    private String orderId;
    private String price;
    private String side;
    private String currency;
    private String symbol;
    private long timestamp;

    public static final  List<String> messageList = new ArrayList<>();
    public static final  List<OrderMessage> orderMessageList = new ArrayList<>();

    public static final ObjectMapper objectMapper = new ObjectMapper();

    public OrderMessage() {
    }

    public OrderMessage(String orderId, String price, String side, String currency, String symbol, long timestamp) {
        this.orderId = orderId;
        this.price = price;
        this.side = side;
        this.currency = currency;
        this.symbol = symbol;
        this.timestamp = timestamp;
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
            OrderMessage msg = null;
            switch (rz) {
                case 1:
                    msg = new OrderMessage(String.valueOf(i),p1,sd1,c1,s1, 0 );
                    break;
                case 2:
                    msg = new OrderMessage(String.valueOf(i),p2,sd2,c2,s2, 0 );
                    break;
                case 3:
                    msg = new OrderMessage(String.valueOf(i),p3,sd3,c3,s3, 0 );
                    break;
                default:
                    break;
            }
            orderMessageList.add(msg);
            String jsonString = objectMapper.writeValueAsString(msg);
            messageList.add(jsonString);
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

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "orderId='" + orderId + '\'' +
                ", price='" + price + '\'' +
                ", side='" + side + '\'' +
                ", currency='" + currency + '\'' +
                ", symbol='" + symbol + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
