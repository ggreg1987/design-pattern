package br.com.dio.domain.rest.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NotEmpty
public class PersonDTO
    extends RepresentationModel<PersonDTO>
    implements Serializable {

  @CPF
  @NotEmpty
  private String cpf;
  @NotEmpty
  private String name;
}
