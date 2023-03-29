package br.com.dio.exception.controller;

import br.com.dio.exception.ApiError;
import br.com.dio.exception.CpfBadRequestException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(CpfBadRequestException.class)
  @ResponseStatus(BAD_REQUEST)
  public ApiError handleCpfException(CpfBadRequestException ex) {
    return message(ex);
  }

  private ApiError message(Exception ex) {
    var message = ex.getMessage();
    return new ApiError(message);
  }
}
