package br.com.einsteinlimeira.beyond.services;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.dto.CasaDTO;
import java.util.List;

/**
 * Define os serviços relacionados à entidade {@link Casa}.
 */
public interface CasaServices extends EntidadeServices<Casa>, EntidadeDTOServices<CasaDTO> {

  /**
   * Retorna as Casas das Cidades <code>idsCidades</code>.
   * 
   * @param cidades
   *   Cidades das quais deseja-se obter as casas.
   * 
   * @return
   *   Casas das Cidades especificadas.
   * 
   * @throws EntidadeServicesException lançada caso ocorra algum problema durante a obtenção das 
   * casas.
   */
  public List<Casa> getCasas(List<Cidade> cidades) throws EntidadeServicesException;
}