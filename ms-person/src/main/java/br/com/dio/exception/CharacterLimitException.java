package br.com.dio.exception;

public class CharacterLimitException extends Exception {

  public CharacterLimitException(String msg) {
    super(msg);
  }
}
