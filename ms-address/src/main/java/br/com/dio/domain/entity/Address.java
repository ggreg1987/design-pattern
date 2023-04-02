package br.com.dio.domain.entity;

import br.com.dio.domain.microservice.MSPersonDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

import static jakarta.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address implements Serializable {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "ID")
  private Long id;
  @NotEmpty
  @Column(name = "STREET", nullable = false, length = 30)
  private String street;
  @NotEmpty
  @Column(name = "NUMBER", nullable = false, length = 10)
  private String number;
  @NotEmpty
  @Column(name = "ZIPCODE", nullable = false, length = 8)
  private String zipcode;

  @ManyToOne
  @JoinColumn(name = "PERSON_CPF")
  private Person person;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Address address = (Address) o;
    return Objects.equals(getId(), address.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
