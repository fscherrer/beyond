package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Musico;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Musico}.
 */
@ManagedBean
@ViewScoped
public class MusicosMB extends BaseManagedBeanEntidade<Musico> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Musico> getEntidadeServices() {
    return ServicesFactory.getFactory().getMusicoServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Musico getNovaEntidade() {
    return new Musico();
  }
}
