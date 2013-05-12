package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Cidade}.
 */
@ManagedBean
@ViewScoped
public class CidadesMB extends BaseManagedBeanEntidade<Cidade> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Cidade> getEntidadeServices() {
    return ServicesFactory.getFactory().getCidadeServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Cidade getNovaEntidade() {
    return new Cidade();
  }
}
