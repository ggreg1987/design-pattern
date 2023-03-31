package br.com.dio.domain.rest.resource;

import br.com.dio.domain.rest.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("address")
@RequiredArgsConstructor
public class AddressResource {

  private final AddressService service;
  private final ObjectMapper mapper;
}
