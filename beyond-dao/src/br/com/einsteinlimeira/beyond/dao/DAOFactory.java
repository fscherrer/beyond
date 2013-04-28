package br.com.einsteinlimeira.beyond.dao;

/**
 * Factory de DAOs (<b>D</b>ata <b>A</b>ccess <b>O</b>bject).<br />
 * Uma implementação deve ser definida através do método {@link #setFactory(DAOFactory)}.
 */
public abstract class DAOFactory {

  /**
   * Implementação a ser utilizada.
   */
  private static DAOFactory factory;

  /**
   * Define a implementação a ser utilizada.
   * 
   * @param factory 
   *   Implementação de {@link DAOFactory} a ser utilizada.
   */
  public static void setFactory(DAOFactory factory) {
    DAOFactory.factory = factory;
  }

  /**
   * Retorna a implementação utilizada.
   * 
   * @return 
   *   Implementação de {@link DAOFactory} utilizada.
   */
  public static DAOFactory getFactory() {
    return factory;
  }

  /**
   * Retorna o DAO para manipulação de {@link Uf}.
   * 
   * @return 
   *    DAO para manipulação de {@link Uf}.
   */
  public abstract UfDAO getUfDAO();
}