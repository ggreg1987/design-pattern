package br.com.dio.domain.rest.resource;

import br.com.dio.domain.entity.Address;
import br.com.dio.domain.rest.dto.AddressDTO;
import br.com.dio.domain.rest.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
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
  public AddressDTO create(@RequestBody Address address) {
    var entity = service.save(address);
    return mapper.convertValue(entity, AddressDTO.class);
  }
}
