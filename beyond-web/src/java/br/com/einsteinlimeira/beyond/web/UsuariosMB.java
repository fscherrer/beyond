package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.UsuarioServices;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class UsuariosMB implements Serializable {

  private List<Usuario> usuarios;

  public UsuariosMB() {
  }

  @PostConstruct
  private void carregarUsuarios() {
    try {
      usuarios = new UsuarioServices().getUsuarios();
    }
    catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Usuários",
          "Ocorreu uma execção ao tentar recuperar a lista de UFs. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  public List<Usuario> getUsuarios() {
    return usuarios;
  }
}
