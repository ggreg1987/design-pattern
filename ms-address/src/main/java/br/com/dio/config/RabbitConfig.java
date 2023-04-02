package br.com.dio.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

  @Value("${rabbitmq.person.exchange}")
  private String exchange;

  @Bean
  public Exchange getExchange() {
    return ExchangeBuilder
        .directExchange(exchange)
        .durable(true)
        .build();
  }
}
