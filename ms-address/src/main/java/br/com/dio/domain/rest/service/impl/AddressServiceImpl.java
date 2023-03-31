package br.com.dio.domain.rest.service.impl;

import br.com.dio.domain.entity.Address;
import br.com.dio.domain.repository.AddressRepository;
import br.com.dio.domain.rest.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {

  private final AddressRepository repository;

  @Override
  @CachePut(value = "address", key = "#result.id")
  public Address save(Address address) {
    return repository.save(address);
  }

  @Override
  @Cacheable(value = "address")
  public Address findById(Long id) {
    log.info("Getting address by id: " + id);
    return repository
        .findById(id)
        .orElseThrow(() -> new AddressNotFoundException("Address not found"));
  }
}
