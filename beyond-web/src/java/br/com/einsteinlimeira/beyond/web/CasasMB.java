package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean para manipulação de {@link Casa}.
 */
@ManagedBean
@RequestScoped
public class CasasMB implements Serializable {

  /**
   * Armazena a lista de {@link Casa}s.
   */
  private List<Casa> casas;

  /**
   * Carrega a lista de {@link Casa}s, que poderão ser obtidas a partir do {@link #getCasas()}.
   */
  @PostConstruct
  public void carregarCasas() {
    try {
      casas = ServicesFactory.getFactory().getCasaServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Casas",
          "Ocorreu uma execção ao tentar recuperar a lista de Casas. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  /**
   * Retorna a lista de {@link Casa}s disponíveis.
   * 
   * @return 
   *   Lista de {@link Casa}s disponíveis.
   */
  public List<Casa> getCasas() {
    return casas;
  }
}