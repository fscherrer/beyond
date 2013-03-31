package br.com.einsteinlimeira.beyond.web.model.dao;

/**
 * Exception para sinalizar problemas relacionados ao acesso aos dados.
 */
public class DAOException extends Exception {

  public DAOException(String message) {
    super(message);
  }

  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }
}