package br.com.dio.domain.microservice;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MSPersonDTO {

  @Id
  private String cpf;
  private String name;
}
