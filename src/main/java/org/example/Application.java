package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.util.backoff.FixedBackOff;

@SpringBootApplication
@EnableFeignClients
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class Application {

  @Bean
  public CommonErrorHandler errorHandler(KafkaOperations<Object, Object> template) {
    return new DefaultErrorHandler(
            new DeadLetterPublishingRecoverer(template), new FixedBackOff(1000L, 2));
  }

  @Bean
  public RecordMessageConverter converter() {
    return new JsonMessageConverter();
  }

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
