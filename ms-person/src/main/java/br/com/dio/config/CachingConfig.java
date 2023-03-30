package br.com.dio.config;

import br.com.dio.domain.entity.Person;
import br.com.dio.domain.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;




@EnableCaching
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CachingConfig {

    private final PersonRepository repository;


    @Cacheable("person")
    public Person getPersonByCpf(String cpf) {
        log.info("Get Person by CPF {}",cpf);
        return repository.findById(cpf).orElse(null);
    }
}
