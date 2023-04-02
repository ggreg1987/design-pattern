package br.com.dio.domain.repository;

import br.com.dio.domain.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface PersonRepository extends JpaRepository<Person,String> {
}
