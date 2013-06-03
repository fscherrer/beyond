package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Entidade;
import java.io.Serializable;
import java.util.List;

/**
 * Define métodos providos por implementações de DAO (<b>D</b>ata <b>A</b>ccess <b>O</b>bject) que 
 * trabalham com DTO (<b>D</b>ata <b>T</b>ransfer <b>O</b>bject).
 * 
 * @param <E> 
 *   Tipo da {@link Entidade} em questão.
 */
public interface EntidadeDTODAO<E extends Entidade> extends Serializable {

  /**
   * Retorna os DTOs da Entidade em questão.<br />
   * 
   * @return
   *   DTOs da Entidade em questão.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção dos dados.
   */
  public List<E> getDTOs() throws DAOException;
}
