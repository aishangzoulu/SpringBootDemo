package com.raylew.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Raymond on 2017/3/18.
 */
public class SpringConfig {
    @Autowired
    private KafkaProperties.Listener listener;

    @Autowired
    private KafkaTemplate<Integer, String> template;

    @Test
    public void testSimple() throws Exception {
        template.send("annotated1", 0, "foo");
        template.flush();
        //assertTrue(this.listener.latch1.await(10, TimeUnit.SECONDS));
    }

    @Configuration
    @EnableKafka
    public class Config {

        @Bean
        ConcurrentKafkaListenerContainerFactory<Integer, String>
        kafkaListenerContainerFactory() {
            ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                    new ConcurrentKafkaListenerContainerFactory<>();
            factory.setConsumerFactory(consumerFactory());
            return factory;
        }

        @Bean
        public ConsumerFactory<Integer, String> consumerFactory() {
            return new DefaultKafkaConsumerFactory<>(consumerConfigs());
        }

        @Bean
        public Map<String, Object> consumerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "0");
            return props;
        }

        @Bean
        public KafkaProperties.Listener listener() {
            return new KafkaProperties.Listener();
        }

        @Bean
        public ProducerFactory<Integer, String> producerFactory() {
            return new DefaultKafkaProducerFactory<>(producerConfigs());
        }

        @Bean
        public Map<String, Object> producerConfigs() {
            Map<String, Object> props = new HashMap<>();
            props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "0");
            return props;
        }

        @Bean
        public KafkaTemplate<Integer, String> kafkaTemplate() {
            return new KafkaTemplate<Integer, String>(producerFactory());
        }

    }
}
