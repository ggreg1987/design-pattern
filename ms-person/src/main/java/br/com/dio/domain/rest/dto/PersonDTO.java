package br.com.dio.domain.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
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

  private String cpf;
  private String name;
}
