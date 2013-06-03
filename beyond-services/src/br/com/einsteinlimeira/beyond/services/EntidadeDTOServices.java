package br.com.einsteinlimeira.beyond.services;

import br.com.einsteinlimeira.beyond.model.Entidade;
import java.util.List;

/**
 * Define serviços providos por implementações de DAO (<b>D</b>ata <b>A</b>ccess <b>O</b>bject) que 
 * trabalham com DTO (<b>D</b>ata <b>T</b>ransfer <b>O</b>bject).
 * 
 * @param <E> 
 *   Tipo da {@link Entidade} em questão.
 */
public interface EntidadeDTOServices<E extends Entidade> extends Services {

  /**
   * Retorna os DTOs da Entidade em questão.<br />
   * 
   * @return
   *   DTOs da Entidade em questão.
   * 
   * @throws EntidadeServicesException lançada caso ocorra algum problema durante a obtenção dos 
   * dados.
   */
  public List<E> getDTOs() throws EntidadeServicesException;
}