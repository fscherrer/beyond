package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.services.CidadeServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * ManagedBean para manipulação de {@link Cidade}.
 */
@ManagedBean
@ViewScoped
public class CidadesMB extends BaseManagedBeanEntidade<Cidade> {
  
  /**
   * Services de Cidade.
   */
  @Inject
  private CidadeServices cidadeServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Cidade> getEntidadeServices() {
    return cidadeServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Cidade getNovaEntidade() {
    return new Cidade();
  }

  /**
   * Retorna a lista de Cidades disponíveis.
   * 
   * @return 
   *   Lista de Cidades disponíveis.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public List<Cidade> getCidades() {
    return getEntidades();
  }
}
