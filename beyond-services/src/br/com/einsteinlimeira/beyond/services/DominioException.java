package br.com.einsteinlimeira.beyond.services;

/**
 * Exception para sinalizar problemas relacionados a regras de negócio.
 */
public class DominioException extends Exception {

  /**
   * Cria uma exception contendo apenas a mensagem <code>message</code>.
   * 
   * @param message 
   *   Mensagem significativa que possibilite a identificação do problema.
   */
  public DominioException(String message) {
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
  public DominioException(String message, Throwable cause) {
    super(message, cause);
  }
}