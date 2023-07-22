import java.io.IOException;

public class Main {

    public static void main(String arg []) throws IOException, InterruptedException {
        AMPSPublisher publisher = new AMPSPublisher();

        AMPSSubscriber subscriber = new AMPSSubscriber();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                publisher.publish();
            }
        }) ;

        t1.start();

        subscriber.subscribe();


    }
}
