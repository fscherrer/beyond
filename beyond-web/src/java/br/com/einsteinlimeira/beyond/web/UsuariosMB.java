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
import org.primefaces.context.RequestContext;

@ManagedBean
@ViewScoped
public class UsuariosMB implements Serializable {

  private List<Usuario> usuarios;

  private Usuario usuario;

  private OperacaoEdicaoEnum operacao;

  public UsuariosMB() {
  }

  public void salvar() {
    boolean exception = false;

    try {
      switch (operacao) {
        case EDICAO:
          new UsuarioServices().atualizar(usuario);
          break;
        case INCLUSAO:
          new UsuarioServices().inserir(usuario);
          carregarUsuarios();
          break;
        default:
          throw new IllegalStateException("Operação não suportada: " + operacao);
      }
    }
    catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível "
          + (operacao == OperacaoEdicaoEnum.INCLUSAO ? "incluir" : "editar") + " o Usuário",
          "Verifique o log da aplicação para mais detalhes"));
      exception = true;
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Ocorreu um erro não esperado",
          "Verifique o log da aplicação para mais detalhes"));
      exception = true;
    }

    RequestContext.getCurrentInstance().addCallbackParam("exception", exception);
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

  public Usuario getUsuario() {
    return usuario;
  }

  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  public void editar(OperacaoEdicaoEnum operacao) {
    this.operacao = operacao;

    if (operacao == OperacaoEdicaoEnum.INCLUSAO) {
      usuario = new Usuario();
    }
  }

  public OperacaoEdicaoEnum getOperacao() {
    return operacao;
  }
}
