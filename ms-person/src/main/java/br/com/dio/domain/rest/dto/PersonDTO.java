package br.com.dio.domain.rest.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
public class PersonDTO
    extends RepresentationModel<PersonDTO>
    implements Serializable {

  @CPF
  private String cpf;
  private String name;
}
