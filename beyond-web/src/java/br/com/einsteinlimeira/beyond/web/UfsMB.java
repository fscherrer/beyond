package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.UfServices;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * ManagedBean para manipulação de {@link Uf}.
 */
@ManagedBean
@ViewScoped
public class UfsMB extends BaseManagedBeanEntidade<Uf> {
  
  /**
   * Services de Uf.
   */
  @Inject
  private UfServices ufServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Uf> getEntidadeServices() {
    return ufServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Uf getNovaEntidade() {
    return new Uf();
  }
}
