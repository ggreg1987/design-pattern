package br.com.dio.domain.rest.service.impl;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.repository.PersonRepository;
import br.com.dio.domain.rest.dto.PersonDTO;
import br.com.dio.domain.rest.service.PersonService;
import br.com.dio.exception.CharacterLimitException;
import br.com.dio.exception.CpfBadRequestException;
import br.com.dio.exception.CpfNotFoundException;
import br.com.dio.rabbitMessage.SendMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.StreamSupport;

import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;

@Service
@RequiredArgsConstructor
@Slf4j
public class PersonServiceImpl implements PersonService {

  private final PersonRepository repository;
  private final SendMessage message;
  private final ObjectMapper mapper;

  @Override
  @CachePut(value = "person", key = "#result.cpf")
  public Person save(Person person) throws CharacterLimitException {
    existsPerson(person);
    if(person.getName().length() < 10) {
      throw new CharacterLimitException("Put Full Name");
    } else {
      try {
        return saveAndSend(person);
      } catch (Exception ex) {
        throw new CharacterLimitException("Character error.");
      }
    }
  }

  @Override
  @Cacheable(value = "person")
  public Person findByCpf(String cpf) {
    log.info("Get Book by ISBN {}", cpf);
    return repository
        .findById(cpf)
        .orElseThrow(() -> new CpfNotFoundException("Cpf not Found"));
  }

  @Override
  @CachePut(value = "person", key = "#result.cpf")
  public Person update(Person person, String cpf) {
    return repository
        .findById(cpf)
        .map(found -> {
          found.setCpf(person.getCpf());
          return person;
        }).orElseThrow(() -> new CpfNotFoundException("Cpf not Found"));
  }

  @Override
  @CacheEvict(value = "person")
  public String delete(String cpf) {
    return repository
        .findById(cpf)
        .map(found -> {
          repository.delete(found);
          log.info("Records deleted {}");
          return cpf;
        })
        .orElseThrow(() -> new CpfBadRequestException("Cpf Not Found"));
  }

  @Override
  public List<Person> findAll(Person person) {
    ExampleMatcher matcher = ExampleMatcher
        .matching()
        .withIgnoreCase()
        .withStringMatcher(CONTAINING);
    Example example = Example.of(person,matcher);

    return StreamSupport
            .stream(repository.findAll(example)
                    .spliterator(),false)
            .toList();
  }

  @Override
  @CacheEvict(value = "person", allEntries = true)
  public String clearPersonCache() {
    log.info("Clearing Person cache...");
    return "Student Cache cleared!";
  }



  private Person existsPerson(Person person) {
    if (repository.existsByCpf(person.getCpf())) {
      throw new CpfBadRequestException("Cpf already exists");
    }
    return person;
  }

  private Person saveAndSend(Person person) {
    var entity = repository.save(person);
    var dto = mapper.convertValue(entity, PersonDTO.class);
    message.sendMessage(dto);
    return entity;
  }
}
