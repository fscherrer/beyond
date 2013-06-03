package br.com.einsteinlimeira.beyond.services;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.dto.BandaDTO;
import java.util.List;

/**
 * Define os serviços relacionados à entidade {@link Banda}.
 */
public interface BandaServices extends EntidadeServices<Banda>, EntidadeDTOServices<BandaDTO> {

  /**
   * Retorna a lista de estilos definidos nas bandas.
   * 
   * @return
   *   Lista de estilos.
   * 
   * @throws EntidadeServicesException lançada caso ocorra algum problema durante a obtenção dos 
   * estilos.
   */
  public List<String> getEstilos() throws EntidadeServicesException;

  /**
   * Retorna as Bandas dos <code>estilos</code>.
   * 
   * @param estilos
   *   Estilos dos quais deseja-se obter as bandas.
   * 
   * @return
   *   Bandas dos estilos especificados.
   * 
   * @throws EntidadeServicesException lançada caso ocorra algum problema durante a obtenção das 
   * bandas.
   */
  public List<Banda> getBandas(List<String> estilos) throws EntidadeServicesException;
}