package br.com.einsteinlimeira.beyond.web;

import java.io.Serializable;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;
import javax.validation.constraints.Size;

/**
 * Managed Bean responsável pelas interações de login.
 */
@ManagedBean
@SessionScoped
public class LoginMB implements Serializable {

  @Size(min = 4, message = "{login.usuario.tamanhoMinimo}")
  private String usuario;

  private String senha;

  private boolean logado;

  public LoginMB() {
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getSenha() {
    return senha;
  }

  public void setSenha(String senha) {
    this.senha = senha;
  }

  public String doLogin() throws InterruptedException {
    logado = true;

    // TODO: agora está tratando com atributo required na view
    if (usuario.isEmpty() || senha.isEmpty()) {
      FacesContext.getCurrentInstance().addMessage(
        null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Por favor informe Usuário e Senha",
        "Os campos Usuário e Senha devem ser preenchidos"));

      return null;
    }

    return "admin";
  }

  public String doLogout() {
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    return "logout";
  }

  public void verificaLogin(ComponentSystemEvent event) {
    if (!logado) {
      ((ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().
        getNavigationHandler()).performNavigation("login");
    }
  }
}
