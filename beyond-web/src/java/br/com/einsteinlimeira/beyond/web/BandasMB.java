package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.services.BandaServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * ManagedBean para manipulação de {@link Banda}.
 */
@ManagedBean
@ViewScoped
public class BandasMB extends BaseManagedBeanEntidade<Banda> {
  
  /**
   * Services de Banda.
   */
  @Inject
  private BandaServices bandaServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Banda> getEntidadeServices() {
    return bandaServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Banda getNovaEntidade() {
    return new Banda();
  }

  /**
   * Retorna a lista de Bandas disponíveis.
   * 
   * @return 
   *   Lista de Bandas disponíveis.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public List<Banda> getBandas() {
    return getEntidades();
  }
}