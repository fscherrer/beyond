package br.com.einsteinlimeira.beyond.services;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Evento;
import java.util.List;

/**
 * Define os serviços relacionados à entidade {@link Evento}.
 */
public interface EventoServices extends EntidadeServices<Evento> {

  /**
   * Retorna a lista de {@link Evento}s filtrados.
   * 
   * @param casas 
   *   Casas filtradas.
   * @param bandas 
   *   Bandas filtradas.
   * 
   * @return 
   *   Lista de {@link Evento}s filtrados.
   * 
   * @throws EntidadeServicesException lançada caso ocorra algum problema durante a obtenção dos
   * eventos.
   */
  public List<Evento> getEventos(List<Casa> casas, List<Banda> bandas) 
      throws EntidadeServicesException;
}