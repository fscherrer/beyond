package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.BandaServices;
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
public class BandasMB implements Serializable {

  private List<Banda> bandas;

  public BandasMB() {
    carregarBandas();
  }

  public void carregarBandas() {

    try {
      bandas = new BandaServices().getBandas();



    } catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
              FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Bandas",
              "Ocorreu uma execção ao tentar recuperar a lista de Bandas. Consulte o log da aplicação para"
              + " mais detalhes"));
    }
  }

  public List<Banda> getBandas() {
    return bandas;
  }
}