package br.com.dio.exception;

public class CpfNotFoundException extends RuntimeException {

  public CpfNotFoundException(String message) {
    super(message);
  }
}
