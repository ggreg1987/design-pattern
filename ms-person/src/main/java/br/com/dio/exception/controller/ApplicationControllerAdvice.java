package br.com.dio.exception.controller;

import br.com.dio.exception.ApiError;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationControllerAdvice {



  private ApiError message(Exception ex) {
    var message = ex.getMessage();
    return new ApiError(message);
  }
}
