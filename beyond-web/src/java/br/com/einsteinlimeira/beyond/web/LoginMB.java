package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
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

  /**
   * Nome de acesso do usuário.
   */
  @Size(min = 4, message = "{login.usuario.tamanhoMinimo}")
  private String usuario;

  /**
   * Senha do usuário.
   */
  private String senha;

  /**
   * Flag para indicar se o usuário está logado.
   */
  private boolean logado;

  /**
   * Binding com a view.
   */
  private UIComponent inputUsuario;

  /**
   * Retorna o nome de acesso do usuário.
   * 
   * @return 
   *   Nome de acesso do usuário.
   */
  public String getUsuario() {
    return usuario;
  }

  /**
   * Define o nome de acesso do usuário.
   * 
   * @param usuario 
   *   Nome de acesso do usuário.
   */
  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  /**
   * Retorna a senha do usuário.
   * 
   * @return 
   *   Senha do usuário.
   */
  public String getSenha() {
    return senha;
  }

  /**
   * Seta a senha do usuário.
   * 
   * @param senha 
   *   Senha do usuário.
   */
  public void setSenha(String senha) {
    this.senha = senha;
  }

  /**
   * Realiza o login.
   * 
   * @return
   *   Outcome para a área administrativa ou <code>null</code> caso a operação falhe (dados do 
   * usuário incorretos).
   */
  public String doLogin() {
    try {
      Usuario usuarioAutenticado =
          ServicesFactory.getFactory().getUsuarioServices().getUsuario(senha, senha);

      if (usuarioAutenticado == null) {
        FacesContext.getCurrentInstance().addMessage(inputUsuario.getClientId(), new FacesMessage(
            FacesMessage.SEVERITY_ERROR, null, "Usuário/senha inválidos"));
      }
      else {
        logado = true;
        return "admin";
      }
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível realizar a autenticação",
          "Mensagem retornada: " + ese.getMessage() + ". Para detalhes consulte o log da aplicação"));
    }

    return null;
  }

  /**
   * Realiza a operação de logout do usuário.
   * 
   * @return 
   *   Outcome para onde deve ir.
   */
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

  /**
   * Retorna o componente bindeado com o campo de entrada do nome do usuário.
   * 
   * @return 
   *   Componente bindeado.
   */
  public UIComponent getInputUsuario() {
    return inputUsuario;
  }

  /**
   * Define o componente bindeado com o campo de entrada do nome do usuário.
   * 
   * @param inputUsuario 
   *   Componente bindeado.
   */
  public void setInputUsuario(UIComponent inputUsuario) {
    this.inputUsuario = inputUsuario;
  }
}
