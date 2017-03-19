package com.raylew.kafka;

import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

/**
 * Created by Raymond on 2017/3/18.
 */
public class Listener {

    private final CountDownLatch latch1 = new CountDownLatch(1);

    @KafkaListener(id = "foo", topics = "topic1")
    public void listen1(String foo) {
        this.latch1.countDown();
    }

}
