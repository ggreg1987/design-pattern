package br.com.dio.exception;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

@Data
public class ApiError {

  private List<String> errors;

  public ApiError(List<String> errors) {
    this.errors = errors;
  }

  public ApiError(String msgError) {
    this.errors = Arrays.asList(msgError);
  }
}
