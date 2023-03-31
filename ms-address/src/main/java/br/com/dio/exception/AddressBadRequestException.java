package br.com.dio.exception;

public class AddressBadRequestException extends RuntimeException {

  public AddressBadRequestException(String message) {
    super(message);
  }
}
