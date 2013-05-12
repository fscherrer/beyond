package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Uf}.
 */
@ManagedBean
@ViewScoped
public class UfsMB extends BaseManagedBeanEntidade<Uf> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Uf> getEntidadeServices() {
    return ServicesFactory.getFactory().getUfServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Uf getNovaEntidade() {
    return new Uf();
  }
}
