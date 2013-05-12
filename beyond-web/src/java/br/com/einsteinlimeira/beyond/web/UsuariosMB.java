package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Usuario;
import br.com.einsteinlimeira.beyond.services.DominioException;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(UsuariosMB.class.getName());

  /**
   * Armazena a lista de {@link Usuario}s.
   */
  private List<Usuario> usuarios;

  /**
   * Conterá a referência a um {@link Usuario} sendo manipulado (para edição, por exemplo).
   */
  private Usuario usuario;

  /**
   * Referência para o {@link Usuario} selecionado na tabela.
   */
  private Usuario usuarioSelecionado;

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
    catch (DominioException de) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível "
          + (operacao == OperacaoEdicaoEnum.INCLUSAO ? "incluir" : "editar") + " o Usuário",
          de.getMessage()));
      exception = true;
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erro não esperado", e);
      
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Ocorreu um erro não esperado",
          "Verifique o log da aplicação para mais detalhes"));
      exception = true;
    }

    RequestContext.getCurrentInstance().addCallbackParam("exception", exception);
  }

  /**
   * Exclui o {@link #usuarioSelecionado}. 
   */
  public void excluir() {
    boolean exception = false;

    try {
      ServicesFactory.getFactory().getUsuarioServices().remover(usuarioSelecionado);
      usuarios.remove(usuarioSelecionado);
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o Usuário",
          "Verifique o log da aplicação para mais detalhes"));
      exception = true;
    }
    catch(DominioException de){
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível excluir o Usuário",
          de.getMessage()));
      exception = true;
    }
    catch (Exception e) {
      LOGGER.log(Level.SEVERE, "Erro não esperado", e);
      
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Ocorreu um erro não esperado",
          "Verifique o log da aplicação para mais detalhes"));
      
      exception = true;
    }

    RequestContext.getCurrentInstance().addCallbackParam("exception", exception);
  }

  /**
   * Prepara para a exclusão do registro selecionado.
   */
  public void preparaExclusao() {
    boolean continuar = false;

    if (usuarioSelecionado == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_WARN, "Nenhum registro selecionado",
          "Por favor selecione um registro para realizar essa operação"));
    }
    else {
      continuar = true;
    }

    RequestContext.getCurrentInstance().addCallbackParam("continuar", continuar);
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
          "Ocorreu uma execção ao tentar recuperar a lista de Uusários. Consulte o log da aplicação para"
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
   * Inicia a edição do {@link #usuarioSelecionado}.
   */
  public void editar() {
    boolean continuar = false;

    if (usuarioSelecionado == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_WARN, "Nenhum registro selecionado",
          "Por favor selecione um registro para realizar essa operação"));
    }
    else {
      continuar = true;
      operacao = OperacaoEdicaoEnum.EDICAO;
      usuario = usuarioSelecionado;
    }

    RequestContext.getCurrentInstance().addCallbackParam("continuar", continuar);
  }

  /**
   * Inicia a inclusão de um {@link Usuario}.
   */
  public void incluir() {
    operacao = OperacaoEdicaoEnum.INCLUSAO;
    usuario = new Usuario();
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

  /**
   * Retorna o {@link Usuario} selecionado.
   * 
   * @return 
   *   O {@link Usuario} selecionado.
   */
  public Usuario getUsuarioSelecionado() {
    return usuarioSelecionado;
  }

  /**
   * Define o {@link Usuario} selecionado.
   * 
   * @param usuarioSelecionado 
   *   {@link Usuario} selecionado.
   */
  public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
    this.usuarioSelecionado = usuarioSelecionado;
  }
}
