package br.com.dio.domain.rest.service;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.repository.PersonRepository;
import br.com.dio.exception.CpfBadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

  private final PersonRepository repository;

  @Override
  public Person save(Person person) {
    existsPerson(person);
    return repository.save(person);
  }

  private Person existsPerson(Person person) {
    if (repository.existsByCpf(person.getCpf())) {
      throw new CpfBadRequestException("Cpf already exists");
    }
    return person;
  }
}
