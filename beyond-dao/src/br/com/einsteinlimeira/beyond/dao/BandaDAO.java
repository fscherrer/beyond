package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Banda;
import java.util.List;

/**
 * Define o DAO de {@link Banda}.
 */
public interface BandaDAO extends EntidadeDAO<Banda> {

  /**
   * Retorna a lista de estilos definidos nas bandas.
   * 
   * @return
   *   Lista de estilos.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção dos estilos.
   */
  public List<String> getEstilos() throws DAOException;

  /**
   * Retorna as Bandas dos <code>estilos</code>.
   * 
   * @param estilos 
   *   Estilos dos quais deseja-se obter as bandas.
   * 
   * @return
   *   Bandas dos estilos especificados.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção das bandas.
   */
  public List<Banda> getBandas(List<String> estilos) throws DAOException;
}