package info.raylew.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Raymond on 2017/3/18.
 */
@Component
public class KafkaConsumerService {
    public static Logger logger = LoggerFactory.getLogger(KafkaConsumerService.class);
    private final CountDownLatch latch = new CountDownLatch(1);

    @KafkaListener(topics = "topic_test")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info("listen2:"+cr.toString());
        latch.countDown();
    }

}
