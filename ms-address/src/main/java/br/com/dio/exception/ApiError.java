package br.com.dio.exception;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public class ApiError {

  private List<String> errors;

  public ApiError(List<String> errors) {
    this.errors = errors;
  }

  public ApiError(String error) {
    this.errors = Arrays.asList(error);
  }
}
