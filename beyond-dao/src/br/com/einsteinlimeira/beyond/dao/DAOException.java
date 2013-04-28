package br.com.einsteinlimeira.beyond.dao;

/**
 * Exception para sinalizar problemas relacionados ao acesso aos dados provido por uma implementação
 * de {@link EntidadeDAO}.
 */
public class DAOException extends Exception {

  /**
   * Cria uma exception contendo apenas a mensagem <code>message</code>.
   * 
   * @param message 
   *   Mensagem significativa que possibilite a identificação do problema.
   */
  public DAOException(String message) {
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
  public DAOException(String message, Throwable cause) {
    super(message, cause);
  }
}