package br.com.dio.exception;

public class CharacterLimitException extends RuntimeException {

  public CharacterLimitException(String message) {
    super(message);
  }
}
