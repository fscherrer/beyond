package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Entidade;
import br.com.einsteinlimeira.beyond.services.DominioException;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 * Base para ManagedBeans para manipulação de {@link Entidade}s.
 * 
 * @param <E> 
 *   Tipo da Entidade.
 */
public abstract class BaseManagedBeanEntidade<E extends Entidade> implements Serializable {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(BaseManagedBeanEntidade.class.getName());

  /**
   * Armazena a lista de entidades.
   */
  private List<E> entidades;

  /**
   * Conterá a referência a uma {@link Entidade} sendo manipulada (para edição, por exemplo).
   */
  private E entidade;

  /**
   * Referência para a {@link Entidade} selecionada na tabela.
   */
  private E entidadeSelecionada;

  /**
   * Identifica a operação a ser realizada.
   */
  private OperacaoEdicaoEnum operacao;

  /**
   * Salva a {@link #getEntidade() entidade} previamente definida, conforme a operação (edição ou 
   * inclusão).
   */
  public void salvar() {
    boolean exception = false;

    try {
      switch (operacao) {
        case EDICAO:
          getEntidadeServices().atualizar(entidade);
          break;
        case INCLUSAO:
          getEntidadeServices().inserir(entidade);
          carregarEntidades();
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
      getEntidadeServices().remover(entidadeSelecionada);
      entidades.remove(entidadeSelecionada);
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível excluir a Entidade",
          "Verifique o log da aplicação para mais detalhes"));
      exception = true;
    }
    catch (DominioException de) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível excluir a Entidade",
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

    if (entidadeSelecionada == null) {
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
   * Carrega a lista de {@link Entidades}s, que poderão ser obtidos a partir do {@link #getEntidades()}.
   */
  @PostConstruct
  protected void carregarEntidades() {
    try {
      entidades = getEntidadeServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Entidade",
          "Ocorreu uma execção ao tentar recuperar a lista de Entidades. Consulte o log da "
          + "aplicação para mais detalhes"));
    }
  }

  /**
   * Retorna a lista de entidades disponíveis.
   * 
   * @return 
   *   Lista de entidades disponíveis.
   */
  public List<E> getEntidades() {
    return entidades;
  }

  /**
   * Retorna uma {@link Entidade} guardada.
   * 
   * @return 
   *   {@link Entidade} previamente guardado através do {@link #setEntidade(Entidade)}.
   */
  public E getEntidade() {
    return entidade;
  }

  /**
   * Define a referência a ser guardada a uma {@link Entidade}.
   * 
   * @param entidade 
   *   {@link Entidade} a ser guardada para operação futura.
   */
  public void setEntidade(E entidade) {
    this.entidade = entidade;
  }

  /**
   * Inicia a edição da {@link #entidadeSelecionada}.
   */
  public void editar() {
    boolean continuar = false;

    if (entidadeSelecionada == null) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_WARN, "Nenhum registro selecionado",
          "Por favor selecione um registro para realizar essa operação"));
    }
    else {
      continuar = true;
      operacao = OperacaoEdicaoEnum.EDICAO;
      entidade = entidadeSelecionada;
    }

    RequestContext.getCurrentInstance().addCallbackParam("continuar", continuar);
  }

  /**
   * Inicia a inclusão de um {@link Entidade}.
   */
  public void incluir() {
    operacao = OperacaoEdicaoEnum.INCLUSAO;
    entidade = getNovaEntidade();
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
   * Retorna a {@link Entidade} selecionada.
   * 
   * @return 
   *   A {@link Entidade} selecionada.
   */
  public E getEntidadeSelecionada() {
    return entidadeSelecionada;
  }

  /**
   * Define a {@link Entidade} selecionada.
   * 
   * @param entidadeSelecionada
   *   {@link Entidade} selecionada.
   */
  public void setEntidadeSelecionada(E entidadeSelecionada) {
    this.entidadeSelecionada = entidadeSelecionada;
  }

  /**
   * Retorna o serviço responsável pelas operações da entidade.
   * 
   * @return 
   *   Implementação de serviço responsável pela entidade.
   */
  public abstract EntidadeServices<E> getEntidadeServices();

  /**
   * Retorna uma nova entidade.
   * 
   * @return 
   *   Nova entidade.
   */
  public abstract E getNovaEntidade();
}
