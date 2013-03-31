package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Uf;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.UfServices;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UfsMB implements Serializable {

  private List<Uf> ufs;

  public UfsMB() {
  }

  @PostConstruct
  private void carregarUfs() {
    try {
      ufs = new UfServices().getUfs();
    }
    catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de UFs",
          "Ocorreu uma execção ao tentar recuperar a lista de UFs. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  public List<Uf> getUfs() {
    return ufs;
  }
}
