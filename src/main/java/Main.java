import java.io.IOException;

public class Main {

    public static void main(String arg []) throws IOException, InterruptedException {
        //ampsRun();

        kafkaRun();


    }

    private static void ampsRun() {
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

    private static void kafkaRun() {
        KafkaPublisher publisher = new KafkaPublisher();

        KafkaSubscriber subscriber = new KafkaSubscriber();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                publisher.publish();
            }
        }) ;

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                subscriber.subscribe();
            }
        }) ;

        //subscriber.subscribe();

        t1.start();
        //t2.start();

    }
}
