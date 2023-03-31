package br.com.dio.domain.rest.resource;

import br.com.dio.domain.entity.Address;
import br.com.dio.domain.rest.dto.AddressDTO;
import br.com.dio.domain.rest.service.AddressService;
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
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressResource {

  private final AddressService service;
  private final ObjectMapper mapper;

  @PostMapping(produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(CREATED)
  public AddressDTO create(@RequestBody Address address) throws CharacterLimitException {
    var entity = service.save(address);
    return toDTO(entity);
  }

  @GetMapping(value = "{id}",produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public AddressDTO findById(@PathVariable Long id) {
    var entity = service.findById(id);
    return toDTO(entity);
  }

  @PutMapping(value = "{id}",produces = APPLICATION_JSON_VALUE,
      consumes = APPLICATION_JSON_VALUE)
  @ResponseStatus(SEE_OTHER)
  public AddressDTO update(@RequestBody Address address ,
                           @PathVariable Long id) {
    var entity = service.findById(id);
    return toDTO(entity);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(NO_CONTENT)
  public void delete(@PathVariable Long id) {
    service.delete(id);
  }

  @GetMapping(produces = APPLICATION_JSON_VALUE)
  @ResponseStatus(OK)
  public List<AddressDTO> findAll(@RequestBody Address address) {
    var entityList = service.findAll(address);
    return entityList
        .stream()
        .map(entity -> toDTO(entity))
        .collect(Collectors.toList());
  }

  @GetMapping("/clear")
  public ResponseEntity<String> clearAddressCache() {
    service.clearAddressCache();
    return new ResponseEntity<>("Address Cache cleared!", OK);
  }

  private AddressDTO toDTO(Address address) {
    var dto = mapper.convertValue(address, AddressDTO.class);
    return hateos(dto);
  }

  private AddressDTO hateos(AddressDTO dto) {
    dto.add(linkTo(methodOn(AddressResource.class)
        .findById(dto.getId())).withSelfRel());
    return dto;
  }
}
