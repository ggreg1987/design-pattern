package br.com.dio.domain.rest.service.impl;

import br.com.dio.domain.entity.Address;
import br.com.dio.domain.repository.AddressRepository;
import br.com.dio.domain.rest.service.AddressService;
import br.com.dio.exception.AddressBadRequestException;
import br.com.dio.exception.AddressNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

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

  @Override
  @CachePut(value = "address", key = "#result.id")
  public Address update(Address address, Long id) {
    return repository
        .findById(id)
        .map(found -> {
          found.setId(address.getId());
          return address;
        }).orElseThrow(() -> new AddressNotFoundException("Address not found"));
  }

  @Override
  @CacheEvict(value = "address")
  public void delete(Long id) {
    repository
        .findById(id)
        .map(found -> {
          repository.deleteById(id);
          log.info("Records deleted {}",found.getId());
          return found;
        }).orElseThrow(() -> new AddressBadRequestException("Address not found"));
  }

  @Override
  public List<Address> findAll(Address address) {
    var match = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(CONTAINING);

    Example example = Example.of(address,match);
    return StreamSupport
        .stream(repository.findAll(example)
            .spliterator(),false)
        .toList();
  }

  @Override
  @CacheEvict(value = "address", allEntries = true)
  public String clearAddressCache() {
    log.info("Clearing Address cache...");
    return "Address Cache cleared!";
  }
}
