package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
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
 * ManagedBean para manipulação de {@link Banda}.
 */
@ManagedBean
@RequestScoped
public class BandasMB implements Serializable {

  /**
   * Armazena a lista de {@link Banda}s.
   */
  private List<Banda> bandas;

  /**
   * Carrega a lista de {@link Banda}s, que poderão ser obtidas a partir do {@link #getBandas()}.
   */
  @PostConstruct
  public void carregarBandas() {
    try {
      bandas = ServicesFactory.getFactory().getBandaServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Bandas",
          "Ocorreu uma execção ao tentar recuperar a lista de Bandas. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  /**
   * Retorna a lista de {@link Banda}s disponíveis.
   * 
   * @return 
   *   Lista de {@link Banda}s disponíveis.
   */
  public List<Banda> getBandas() {
    return bandas;
  }
}