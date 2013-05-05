package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Musico;
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
 * ManagedBean para manipulação de {@link Musico}.
 */
@ManagedBean
@ViewScoped
public class MusicosMB implements Serializable {

  /**
   * Armazena a lista de {@link Musico}s.
   */
  private List<Musico> musicos;

  /**
   * Carrega a lista de {@link Musico}s, que poderão ser obtidos a partir do {@link #getMusicos()}.
   */
  @PostConstruct
  private void carregarMusicos() {
    try {
      musicos = ServicesFactory.getFactory().getMusicoServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Músicos",
          "Ocorreu uma exceção ao tentar recuperar a lista de Músicos. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  /**
   * Retorna a lista de {@link Musico}s disponíveis.
   * 
   * @return 
   *   Lista de {@link Musico}s disponíveis.
   */
  public List<Musico> getMusicos() {
    return musicos;
  }
}
