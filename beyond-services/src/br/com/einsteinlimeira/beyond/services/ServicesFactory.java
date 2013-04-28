package br.com.einsteinlimeira.beyond.services;

import br.com.einsteinlimeira.beyond.model.Uf;

/**
 * Factory de serviços.<br />
 * Uma implementação deve ser definida através do método {@link #setFactory(ServicesFactory)}.
 */
public abstract class ServicesFactory {

  /**
   * Implementação a ser utilizada.
   */
  private static ServicesFactory factory;

  /**
   * Define a implementação a ser utilizada.
   * 
   * @param factory 
   *   Implementação de {@link ServicesFactory} a ser utilizada.
   */
  public static void setFactory(ServicesFactory factory) {
    ServicesFactory.factory = factory;
  }

  /**
   * Retorna a implementação utilizada.
   * 
   * @return 
   *   Implementação de {@link ServicesFactory} utilizada.
   */
  public static ServicesFactory getFactory() {
    return factory;
  }

  /**
   * Retorna o provedor de serviços relacionados a {@link Uf}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Uf}.
   */
  public abstract UfServices getUfServices();
}