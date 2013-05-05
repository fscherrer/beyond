package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * ManagedBean para manipulação de {@link Usuario}.
 */
@ManagedBean
@ViewScoped
public class UsuariosMB implements Serializable {

  /**
   * Armazena a lista de {@link Usuario}s.
   */
  private List<Usuario> usuarios;

  /**
   * Conterá a referência a um {@link Usuario} sendo manipulado (para edição, por exemplo).
   */
  private Usuario usuario;

  /**
   * Identifica a operação a ser realizada.
   */
  private OperacaoEdicaoEnum operacao;

  /**
   * Salva o {@link #getUsuario() usuário} previamente definido, conforme a operação (edição ou 
   * inclusão).
   */
  public void salvar() {
    boolean exception = false;

    try {
      switch (operacao) {
        case EDICAO:
          ServicesFactory.getFactory().getUsuarioServices().atualizar(usuario);
          break;
        case INCLUSAO:
          ServicesFactory.getFactory().getUsuarioServices().inserir(usuario);
          carregarUsuarios();
          break;
        default:
          throw new IllegalStateException("Operação não suportada: " + operacao);
      }
    }
    catch (EntidadeServicesException ese) {
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

  /**
   * Exclui o {@link #getUsuario() usuário} previamente definido. 
   */
  public void excluir() {
    boolean exception = false;

    try {
      ServicesFactory.getFactory().getUsuarioServices().remover(usuario);
      usuarios.remove(usuario);
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o Usuário",
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

  /**
   * Carrega a lista de {@link Usuario}s, que poderão ser obtidos a partir do {@link #getEventos()}.
   */
  @PostConstruct
  private void carregarUsuarios() {
    try {
      usuarios = ServicesFactory.getFactory().getUsuarioServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Usuários",
          "Ocorreu uma execção ao tentar recuperar a lista de UFs. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  /**
   * Retorna a lista de {@link Usuario}s disponíveis.
   * 
   * @return 
   *   Lista de {@link Usuario}s disponíveis.
   */
  public List<Usuario> getUsuarios() {
    return usuarios;
  }

  /**
   * Retorna um {@link Usuario} guardado.
   * 
   * @return 
   *   {@link Usuario} previamente guardado através do {@link #setUsuario(Usuario)}.
   */
  public Usuario getUsuario() {
    return usuario;
  }

  /**
   * Define a referência a ser guardada a um {@link Usuario}.
   * 
   * @param usuario 
   *   {@link Usuario} a ser guardado para operação futura.
   */
  public void setUsuario(Usuario usuario) {
    this.usuario = usuario;
  }

  /**
   * Inicia a edição de um {@link Usuario}.
   * 
   * @param operacao 
   *   Operação de edição a ser realizada ({@link OperacaoEdicaoEnum#EDICAO} ou
   * {@link OperacaoEdicaoEnum#INCLUSAO}).
   */
  public void editar(OperacaoEdicaoEnum operacao) {
    this.operacao = operacao;

    if (operacao == OperacaoEdicaoEnum.INCLUSAO) {
      usuario = new Usuario();
    }
  }

  /**
   * Retorna a operação atual.
   * 
   * @return 
   *   Operação sendo realizada.
   */
  public OperacaoEdicaoEnum getOperacao() {
    return operacao;
  }
}
