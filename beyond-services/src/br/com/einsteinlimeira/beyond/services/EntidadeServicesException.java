package br.com.einsteinlimeira.beyond.services;

/**
 * Exception para sinalizar problemas relacionados aos serviços providos por uma implementação de
 * {@link CadastroServices}.
 */
public class EntidadeServicesException extends Exception {

  /**
   * Cria uma exception contendo apenas a mensagem <code>message</code>.
   * 
   * @param message 
   *   Mensagem significativa que possibilite a identificação do problema.
   */
  public EntidadeServicesException(String message) {
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
  public EntidadeServicesException(String message, Throwable cause) {
    super(message, cause);
  }
}