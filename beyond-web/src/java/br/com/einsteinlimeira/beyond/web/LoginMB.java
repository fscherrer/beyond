package br.com.einsteinlimeira.beyond.web;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

    return "admin";
  }

  public String doLogout() {
    logado = false;
    FacesContext.getCurrentInstance().getExternalContext().invalidateSession();

    return "logout";
  }

  /**
   * Retorna se o usuário está logado.
   * 
   * @return 
   *   <code>True</code> se estiver logado.
   */
  public boolean isLogado() {
    return logado;
  }
}
