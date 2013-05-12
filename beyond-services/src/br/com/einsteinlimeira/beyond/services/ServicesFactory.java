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
   * Retorna o provedor de serviços relacionados a {@link Banda}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Banda}.
   */
  public abstract BandaServices getBandaServices();

  /**
   * Retorna o provedor de serviços relacionados a {@link Casa}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Casa}.
   */
  public abstract CasaServices getCasaServices();

  /**
   * Retorna o provedor de serviços relacionados a {@link Evento}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Evento}.
   */
  public abstract EventoServices getEventoServices();

  /**
   * Retorna o provedor de serviços relacionados a {@link Musico}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Musico}.
   */
  public abstract MusicoServices getMusicoServices();

  /**
   * Retorna o provedor de serviços relacionados a {@link Uf}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Uf}.
   */
  public abstract UfServices getUfServices();

  /**
   * Retorna o provedor de serviços relacionados a {@link Cidade}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Cidade}.
   */
  public abstract CidadeServices getCidadeServices();

  /**
   * Retorna o provedor de serviços relacionados a {@link Usuario}.
   * 
   * @return 
   *   Provedor de serviços relacionados a {@link Usuario}.
   */
  public abstract UsuarioServices getUsuarioServices();
}