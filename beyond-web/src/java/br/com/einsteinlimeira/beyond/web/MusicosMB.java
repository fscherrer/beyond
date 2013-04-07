package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Musico;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.MusicoServices;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class MusicosMB implements Serializable {

  private List<Musico> musicos;

  public MusicosMB() {
  }

  @PostConstruct
  private void carregarMusicos() {
    try {
      musicos = new MusicoServices().getMusicos();
    }
    catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Músicos",
          "Ocorreu uma exceção ao tentar recuperar a lista de Músicos. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  public List<Musico> getMusicos() {
    return musicos;
  }
}
