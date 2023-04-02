package br.com.dio.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {

  @Id
  private String cpf;
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return Objects.equals(getCpf(), person.getCpf());
  }
}
