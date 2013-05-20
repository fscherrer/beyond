package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Musico;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.MusicoServices;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * ManagedBean para manipulação de {@link Musico}.
 */
@ManagedBean
@ViewScoped
public class MusicosMB extends BaseManagedBeanEntidade<Musico> {
  
  /**
   * Services de Músico.
   */
  @Inject
  private MusicoServices musicoServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Musico> getEntidadeServices() {
    return musicoServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Musico getNovaEntidade() {
    return new Musico();
  }
}
