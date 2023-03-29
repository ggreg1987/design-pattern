package br.com.dio.exception.controller;

import br.com.dio.exception.ApiError;
import br.com.dio.exception.CpfBadRequestException;
import br.com.dio.exception.CpfNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class ApplicationControllerAdvice {

  @ExceptionHandler(CpfBadRequestException.class)
  @ResponseStatus(BAD_REQUEST)
  public ApiError handleCpfException(CpfBadRequestException ex) {
    return message(ex);
  }

  @ExceptionHandler(CpfNotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ApiError handleCpfNotFoundException(CpfNotFoundException ex) {
    return message(ex);
  }

  private ApiError message(Exception ex) {
    var message = ex.getMessage();
    return new ApiError(message);
  }
}
