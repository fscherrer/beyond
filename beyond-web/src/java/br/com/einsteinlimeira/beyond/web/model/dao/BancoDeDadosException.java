package br.com.einsteinlimeira.beyond.web.model.dao;

/**
 * Exception para sinalizar problemas relacionados ao acesso ao banco de dados.
 */
public class BancoDeDadosException extends Exception {

  public BancoDeDadosException(String message) {
    super(message);
  }

  public BancoDeDadosException(String message, Throwable cause) {
    super(message, cause);
  }
}