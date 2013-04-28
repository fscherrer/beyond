package br.com.einsteinlimeira.beyond.dao.impl;

/**
 * Exception para sinalizar problemas relacionados ao acesso ao banco de dados.
 */
public class BancoDeDadosException extends Exception {

  /**
   * Cria uma exception contendo apenas a mensagem <code>message</code>.
   * 
   * @param message 
   *   Mensagem significativa que possibilite a identificação do problema.
   */
  public BancoDeDadosException(String message) {
    super(message);
  }

  /**
   * Cria uma exception contendo a mensagem <code>message</code> e a causa <code>cause</code>.
   * 
   * @param message 
   *   Mensagem significativa que possibilite a identificação do problema.
   * @param cause 
   *   Causa original da exception.
   */
  public BancoDeDadosException(String message, Throwable cause) {
    super(message, cause);
  }
}