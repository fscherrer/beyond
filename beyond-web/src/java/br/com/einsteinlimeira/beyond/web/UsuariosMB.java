package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Usuario}.
 */
@ManagedBean
@ViewScoped
public class UsuariosMB extends BaseManagedBeanEntidade<Usuario> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Usuario> getEntidadeServices() {
    return ServicesFactory.getFactory().getUsuarioServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Usuario getNovaEntidade() {
    return new Usuario();
  }
}