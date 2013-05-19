package br.com.einsteinlimeira.beyond.dao;

import br.com.einsteinlimeira.beyond.model.Evento;
import java.util.List;

/**
 * Define o DAO de {@link Evento}.
 */
public interface EventoDAO extends EntidadeDAO<Evento> {

  /**
   * Retorna a lista de {@link Evento}s filtrados.
   * 
   * @param idsCasas
   *   IDs das casas filtradas.
   * @param idsBandas
   *   IDs das bandas filtradas.
   * 
   * @return 
   *   Lista de {@link Evento}s filtrados.
   * 
   * @throws DAOException lançada caso ocorra algum problema durante a obtenção dos eventos.
   */
  public List<Evento> getEventos(int[] idsCasas, int[] idsBandas) throws DAOException;
}