package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean para manipulação de {@link Uf}.
 */
@ManagedBean
@ViewScoped
public class UfsMB implements Serializable {

  /**
   * Armazena a lista de {@link Uf}s.
   */
  private List<Uf> ufs;

  /**
   * Carrega a lista de {@link Uf}s, que poderão ser obtidos a partir do {@link #getUfs()}.
   */
  @PostConstruct
  private void carregarUfs() {
    try {
      ufs = ServicesFactory.getFactory().getUfServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de UFs",
          "Ocorreu uma execção ao tentar recuperar a lista de UFs. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  /**
   * Retorna a lista de {@link Uf}s disponíveis.
   * 
   * @return 
   *   Lista de {@link Uf}s disponíveis.
   */
  public List<Uf> getUfs() {
    return ufs;
  }
}
