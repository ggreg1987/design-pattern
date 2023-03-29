package br.com.dio.domain.rest.resource;

import br.com.dio.domain.rest.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonResource {

  private final PersonService service;
  private final ObjectMapper mapper;

}
