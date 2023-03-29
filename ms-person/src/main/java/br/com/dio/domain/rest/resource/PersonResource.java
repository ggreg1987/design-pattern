package br.com.dio.domain.rest.resource;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.rest.dto.PersonDTO;
import br.com.dio.domain.rest.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("person")
@RequiredArgsConstructor
public class PersonResource {

  private final PersonService service;
  private final ObjectMapper mapper;

  @PostMapping(produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public PersonDTO create(@RequestBody Person person) throws Exception {
    var entity = service.save(person);
    return mapper.convertValue(entity, PersonDTO.class);
  }

  @GetMapping(value = "{cpf}",produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public PersonDTO findByCpf(@PathVariable String cpf) {
    var entity = service.findByCpf(cpf);
    return mapper.convertValue(entity, PersonDTO.class);
  }

  @PutMapping(value = "{cpf}",produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  public PersonDTO update(@RequestBody Person person ,@PathVariable String cpf) {
    var entity = service.update(person,cpf);
    return mapper.convertValue(entity, PersonDTO.class);
  }

  @DeleteMapping("{cpf}")
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable String cpf) {
    service.delete(cpf);
  }

}
