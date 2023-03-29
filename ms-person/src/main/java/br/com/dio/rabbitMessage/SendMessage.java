package br.com.dio.rabbitMessage;

import br.com.dio.domain.rest.dto.PersonDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendMessage {

  @Value("${rabbitmq.person.exchange}")
  private String exchange;
  @Value("${rabbitmq.person.routing-key}")
  private String routingKey;

  private final RabbitTemplate template;

  public void sendMessage(PersonDTO dto) {
    System.out.println(dto);
    System.out.println(exchange);
    System.out.println(routingKey);
    template.convertAndSend(exchange,routingKey,dto);
  }
}
