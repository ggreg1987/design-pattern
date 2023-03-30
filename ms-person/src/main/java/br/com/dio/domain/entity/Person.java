package br.com.dio.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PERSON")
public class Person implements Serializable {

  @Id
  @Column(name = "CPF", length = 11)
  private String cpf;
  @Column(name = "NAME", nullable = false, length = 30)
  private String name;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Person person = (Person) o;
    return Objects.equals(getCpf(), person.getCpf());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getCpf());
  }
}
