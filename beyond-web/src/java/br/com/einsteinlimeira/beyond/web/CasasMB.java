package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.CasaServices;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Iron
 */
@ManagedBean
@RequestScoped
public class CasasMB implements Serializable {

  private List<Casa> casas;

  public CasasMB() {
    carregarCasas();
  }

  public void carregarCasas() {

    try {
      casas = new CasaServices().getEventos();



    } catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
              FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Casas",
              "Ocorreu uma execção ao tentar recuperar a lista de Casas. Consulte o log da aplicação para"
              + " mais detalhes"));
    }
  }

  public List<Casa> getCasas() {
    return casas;
  }
}