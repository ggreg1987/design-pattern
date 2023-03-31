package br.com.dio.exception.controller;

import br.com.dio.exception.AddressBadRequestException;
import br.com.dio.exception.AddressNotFoundException;
import br.com.dio.exception.ApiError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
public class ApiControllerAdvice {

  @ExceptionHandler(AddressNotFoundException.class)
  @ResponseStatus(NOT_FOUND)
  public ApiError handleAddressNotFoundException(AddressNotFoundException ex) {
    var msg = ex.getMessage();
    return new ApiError(msg);
  }

  @ExceptionHandler(AddressBadRequestException.class)
  @ResponseStatus(NOT_FOUND)
  public ApiError handleAddressBadRequestException(AddressBadRequestException ex) {
    var msg = ex.getMessage();
    return new ApiError(msg);
  }
}
