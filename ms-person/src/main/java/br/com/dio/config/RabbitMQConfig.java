package br.com.dio.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

  @Value("${rabbitmq.person.exchange}")
  private String exchange;

  @Bean
  public Exchange getExchange() {
    return ExchangeBuilder
        .directExchange(exchange)
        .durable(true)
        .build();
  }

  @Bean
  @Autowired
  public RabbitTemplate getTemplate(ConnectionFactory factory) {
    final var rbTemplate = new RabbitTemplate(factory);
    rbTemplate.setMessageConverter(convertMessage());
    return rbTemplate;
  }

  @Bean
  public MessageConverter convertMessage() {
    return new Jackson2JsonMessageConverter();
  }
}
