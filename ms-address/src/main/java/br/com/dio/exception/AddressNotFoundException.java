package br.com.dio.exception;

public class AddressNotFoundException extends RuntimeException {
  
  public AddressNotFoundException(String message) {
    super(message);
  }
}
