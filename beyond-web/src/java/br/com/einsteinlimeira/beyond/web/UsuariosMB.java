package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.UsuarioServices;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * ManagedBean para manipulação de {@link Usuario}.
 */
@ManagedBean
@ViewScoped
public class UsuariosMB extends BaseManagedBeanEntidade<Usuario> {
  
  /**
   * Services de Usuário.
   */
  @Inject
  private UsuarioServices usuarioServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Usuario> getEntidadeServices() {
    return usuarioServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Usuario getNovaEntidade() {
    return new Usuario();
  }
}