package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 * ManagedBean para manipulação de {@link Casa}.
 */
@ManagedBean
@ViewScoped
public class CasasMB extends BaseManagedBeanEntidade<Casa> {

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Casa> getEntidadeServices() {
    return ServicesFactory.getFactory().getCasaServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Casa getNovaEntidade() {
    return new Casa();
  }

  /**
   * Retorna a lista de Casas disponíveis.
   * 
   * @return 
   *   Lista de Casas disponíveis.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public List<Casa> getCasas() {
    return getEntidades();
  }
}