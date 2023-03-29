package br.com.dio.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Person implements Serializable {

  @Id
  @Column(length = 11)
  private String cpf;
  @Column(nullable = false,length = 30)
  private String name;
}
