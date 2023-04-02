package br.com.dio.domain.message;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.microservice.MSPersonDTO;
import br.com.dio.domain.repository.PersonRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ReceiveMessage {

  private final PersonRepository repository;
  private final ObjectMapper mapper;

  @RabbitListener(queues = {"${rabbitmq.person.queue}"})
  public void receiveMessage(@Payload MSPersonDTO dto) {
    System.out.println(dto);
    var entity = mapper.convertValue(dto,Person.class);
    repository.save(entity);
  }
}
