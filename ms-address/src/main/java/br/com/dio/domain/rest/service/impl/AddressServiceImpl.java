package br.com.dio.domain.rest.service.impl;

import br.com.dio.domain.entity.Address;
import br.com.dio.domain.repository.AddressRepository;
import br.com.dio.domain.rest.service.AddressService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository repository;
  private final ObjectMapper mapper;

  @Override
  public Address save(Address address) {
    return null;
  }
}
