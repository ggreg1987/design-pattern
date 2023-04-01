package br.com.dio.domain.rest.resource;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.rest.dto.PersonDTO;
import br.com.dio.domain.rest.service.PersonService;
import br.com.dio.exception.CharacterLimitException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
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
  public PersonDTO create(@RequestBody Person person) throws CharacterLimitException {
    var entity = service.save(person);
    return toDTO(entity);
  }

  @GetMapping(value = "{cpf}",produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public PersonDTO findByCpf(@PathVariable String cpf) {
    var entity = service.findByCpf(cpf);
    return toDTO(entity);
  }

  @PutMapping(value = "{cpf}",produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(SEE_OTHER)
  public PersonDTO update(@RequestBody Person person ,@PathVariable String cpf) {
    var entity = service.update(person,cpf);
    return toDTO(entity);
  }

  @DeleteMapping("{cpf}")
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable String cpf) {
    service.delete(cpf);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public List<PersonDTO> findAll(@RequestBody Person person) {
    var entityList = service.findAll(person);
    return entityList
        .stream()
        .map(onePerson -> toDTO(onePerson))
            .collect(Collectors.toList());
  }

  @GetMapping("/clear")
  public ResponseEntity<String> clearPersonCache() {
    service.clearPersonCache();
    return new ResponseEntity<>("Person Cache cleared!", OK);
  }



  private PersonDTO toDTO(Person person) {
    var dto = mapper.convertValue(person, PersonDTO.class);
    return hateos(dto);
  }

  private PersonDTO hateos(PersonDTO dto) {
    dto.add(linkTo(methodOn(PersonResource.class)
        .findByCpf(dto.getCpf())).withSelfRel());
    return dto;
  }

}
