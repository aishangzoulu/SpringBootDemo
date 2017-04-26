package info.raylew.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Raymond on 2017/3/18.
 */
@Component
public class Listener {

    private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(topics = "topic_test")
    public void listen1(String message) {
        System.out.println("listen1:"+message);
        this.latch1.countDown();
    }

}
