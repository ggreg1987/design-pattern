package br.com.dio.domain.rest.service.impl;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.repository.PersonRepository;
import br.com.dio.domain.rest.service.PersonService;
import br.com.dio.exception.CharacterLimitException;
import br.com.dio.exception.CpfBadRequestException;
import br.com.dio.exception.CpfNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository repository;

  @Override
  public Person save(Person person) {
    existsPerson(person);
    try {
      return repository.save(person);
    } catch (DataIntegrityViolationException ex) {
      throw new CharacterLimitException("Character limit.");
    }

  }

  @Override
  public Person findByCpf(String cpf) {
    return repository
        .findById(cpf)
        .orElseThrow(() -> new CpfNotFoundException("Cpf not Found"));
  }

  private Person existsPerson(Person person) {
    if (repository.existsByCpf(person.getCpf())) {
      throw new CpfBadRequestException("Cpf already exists");
    }
    return person;
  }
}
