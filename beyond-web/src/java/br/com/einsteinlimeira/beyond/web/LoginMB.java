package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.UsuarioServices;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
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

  /**
   * Binding com a view.
   */
  private UIComponent inputUsuario;

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
    try {
      Usuario usuarioAutenticado = new UsuarioServices().getUsuario(usuario, senha);

      if (usuarioAutenticado == null) {
        FacesContext.getCurrentInstance().addMessage(inputUsuario.getClientId(), new FacesMessage(
            FacesMessage.SEVERITY_ERROR, null, "Usuário/senha inválidos"));
      }
      else {
        logado = true;
        return "admin";
      }
    }
    catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível realizar a autenticação",
          "Mensagem retornada: " + daoe.getMessage() + ". Para detalhes consulte o log da aplicação"));
    }

    return null;
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

  public UIComponent getInputUsuario() {
    return inputUsuario;
  }

  public void setInputUsuario(UIComponent inputUsuario) {
    this.inputUsuario = inputUsuario;
  }
}
