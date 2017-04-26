package info.raylew;

import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class WebgisApplication implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(WebgisApplication.class, args);
	}

	public static Logger logger = LoggerFactory.getLogger(Application.class);

	@Autowired
	private KafkaTemplate<Integer, String> template;

	private final CountDownLatch latch = new CountDownLatch(3);

	@Override
	public void run(String... args) throws Exception {
		this.template.send("topic_test", "I");
		this.template.send("topic_test", "am");
		this.template.send("topic_test", "a test");
		latch.await(60, TimeUnit.SECONDS);
		logger.info("All received");
	}

}
