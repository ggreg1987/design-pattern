package br.com.dio.domain.rest.service;

import br.com.dio.domain.entity.Person;
import br.com.dio.exception.CharacterLimitException;

import java.util.List;

public interface PersonService {

  Person save(Person person) throws CharacterLimitException;
  Person findByCpf(String cpf);
  Person update(Person person,String cpf);
  String delete(String cpf);
  List<Person> findAll(Person person);
  String clearPersonCache();


}
