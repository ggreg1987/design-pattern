package br.com.dio.domain.rest.service;

import br.com.dio.domain.entity.Address;

import java.util.List;

public interface AddressService {

  Address save(Address address);
  Address findById(Long id);
  Address update(Address address,Long id);
  void delete(Long id);
  List<Address> findAll(Address address);
}
