package br.com.dio.exception;

import org.springframework.dao.DataIntegrityViolationException;

public class CharacterLimitException extends DataIntegrityViolationException {

  public CharacterLimitException(String msg) {
    super(msg);
  }
}
