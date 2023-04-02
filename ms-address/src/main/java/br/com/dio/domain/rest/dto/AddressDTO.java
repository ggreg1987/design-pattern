package br.com.dio.domain.rest.dto;

import br.com.dio.domain.microservice.MSPersonDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;

@Getter
@Setter
public class AddressDTO
    extends RepresentationModel<AddressDTO>
    implements Serializable {

  private Long id;
  private String street;
  private String number;
  private String zipcode;
  private MSPersonDTO personDTO;
}
