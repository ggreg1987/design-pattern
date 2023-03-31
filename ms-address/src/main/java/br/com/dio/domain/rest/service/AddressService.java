package br.com.dio.domain.rest.service;

import br.com.dio.domain.entity.Address;

public interface AddressService {

  Address save(Address address);
  Address findById(Long id);
  Address update(Address address,Long id);
  void delete(Long id);
}
