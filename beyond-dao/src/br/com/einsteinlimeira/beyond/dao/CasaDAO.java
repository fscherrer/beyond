package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Casa;
import java.util.List;

/**
 * Define o DAO de {@link Casa}.
 */
public interface CasaDAO extends EntidadeDAO<Casa> {

  /**
   * Retorna as Casas das Cidades <code>idsCidades</code>.
   * 
   * @param idsCidades
   *   IDs das Cidades das quais deseja-se obter as casas.
   * 
   * @return
   *   Casas das Cidades especificadas.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção das casas.
   */
  public List<Casa> getCasas(int[] idsCidades) throws DAOException;
}