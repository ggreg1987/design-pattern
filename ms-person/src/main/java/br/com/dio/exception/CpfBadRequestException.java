package br.com.dio.exception;

public class CpfBadRequestException extends RuntimeException {

  public CpfBadRequestException(String message) {
    super(message);
  }
}
