package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Endereco;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 * ManagedBean para manipulação de {@link Casa}.
 */
@ManagedBean
@ViewScoped
public class CasasMB extends BaseManagedBeanEntidade<Casa> {

  /**
   * Services de Casa.
   */
  @Inject
  private CasaServices casaServices;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Casa> getEntidadeServices() {
    return casaServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Casa getNovaEntidade() {
    Casa casa = new Casa();
    casa.setEndereco(new Endereco());
    return casa;
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

  /**
   * Define a referência a ser guardada a uma {@link Casa}.
   * 
   * @param casa  
   *   {@link Casa} a ser guardada para operação futura.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public void setCasa(Casa casa) {
    setEntidade(casa);
  }

  /**
   * Retorna uma {@link Casa} guardada.
   * 
   * @return 
   *   {@link Casa} previamente guardada através do {@link #setCasa(Casa)}.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public Casa getCasa() {
    return getEntidade();
  }
}