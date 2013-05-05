package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Endereco;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.model.Musico;
import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.model.Usuario;

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
   * Retorna o DAO para manipulação de {@link Banda}.
   * 
   * @return 
   *    DAO para manipulação de {@link Banda}.
   */
  public abstract BandaDAO getBandaDAO();

  /**
   * Retorna o DAO para manipulação de {@link Casa}.
   * 
   * @return 
   *    DAO para manipulação de {@link Casa}.
   */
  public abstract CasaDAO getCasaDAO();

  /**
   * Retorna o DAO para manipulação de {@link Cidade}.
   * 
   * @return 
   *    DAO para manipulação de {@link Cidade}.
   */
  public abstract CidadeDAO getCidadeDAO();

  /**
   * Retorna o DAO para manipulação de {@link Endereco}.
   * 
   * @return 
   *    DAO para manipulação de {@link Endereco}.
   */
  public abstract EnderecoDAO getEnderecoDAO();

  /**
   * Retorna o DAO para manipulação de {@link Evento}.
   * 
   * @return 
   *    DAO para manipulação de {@link Evento}.
   */
  public abstract EventoDAO getEventoDAO();

  /**
   * Retorna o DAO para manipulação de {@link Musico}.
   * 
   * @return 
   *    DAO para manipulação de {@link Musico}.
   */
  public abstract MusicoDAO getMusicoDAO();

  /**
   * Retorna o DAO para manipulação de {@link Uf}.
   * 
   * @return 
   *    DAO para manipulação de {@link Uf}.
   */
  public abstract UfDAO getUfDAO();

  /**
   * Retorna o DAO para manipulação de {@link Usuario}.
   * 
   * @return 
   *   DAO para manipulação de {@link Usuario}.
   */
  public abstract UsuarioDAO getUsuarioDAO();
}