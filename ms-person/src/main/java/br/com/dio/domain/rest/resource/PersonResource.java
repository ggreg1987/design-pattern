package br.com.dio.domain.rest.resource;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.rest.dto.PersonDTO;
import br.com.dio.domain.rest.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
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
  public PersonDTO create(@RequestBody Person person) throws Exception {
    var entity = service.save(person);
    return toDTO(entity);
  }

  @GetMapping(value = "{cpf}",produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public PersonDTO findByCpf(@PathVariable String cpf) {
    var entity = service.findByCpf(cpf);
    var dto = mapper.convertValue(entity, PersonDTO.class);
    return toDTO(entity);
  }

  @PutMapping(value = "{cpf}",produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(SEE_OTHER)
  public PersonDTO update(@RequestBody Person person ,@PathVariable String cpf) {
    var entity = service.update(person,cpf);
    var dto = mapper.convertValue(entity, PersonDTO.class);
    return toDTO(entity);
  }

  @DeleteMapping("{cpf}")
  @ResponseStatus(NO_CONTENT)
  public String delete(@PathVariable String cpf) {
    return service.delete(cpf);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public List<PersonDTO> findAll(@RequestBody Person person) {
    var entityList = service.findAll(person);
    return entityList
        .stream()
        .map(onePerson -> {
          return toDTO(onePerson);
        }).collect(Collectors.toList());
  }

  @GetMapping("/clear")
  @ResponseStatus(OK)
  public String clearPersonCache() {
    return clearPersonCache();
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
