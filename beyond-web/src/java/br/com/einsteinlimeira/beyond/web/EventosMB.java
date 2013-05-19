package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.EventoServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 * ManagedBean para manipulação de {@link Evento}.
 */
@ManagedBean
@ViewScoped
public class EventosMB extends BaseManagedBeanEntidade<Evento> {

  /**
   * Logger para logar mensagens.
   */
  private final static Logger LOGGER = Logger.getLogger(EventosMB.class.getName());

  /**
   * Armazena as bandas selecionadas.
   */
  private List<Banda> bandasSelecionadas;

  /**
   * Armazena a quantidade de bandas selecionadas.
   */
  private int quantidadeBandasSelecionadas;

  /**
   * Armazena as casas selecionadas.
   */
  private List<Casa> casasSelecionadas;

  /**
   * Armazena a quantidade de casas selecionadas.
   */
  private int quantidadeCasasSelecionadas;

  /**
   * Armazena as cidades selecionadas.
   */
  private List<Cidade> cidadesSelecionadas;

  /**
   * Armazena a quantidade de cidades selecionadas.
   */
  private int quantidadeCidadesSelecionadas;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Evento> getEntidadeServices() {
    return ServicesFactory.getFactory().getEventoServices();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Evento getNovaEntidade() {
    return new Evento();
  }

  /**
   * Retorna as bandas selecionadas.
   * 
   * @return 
   *   As bandas selecionadas.
   */
  public List<Banda> getBandasSelecionadas() {
    return bandasSelecionadas;
  }

  /**
   * Define as bandas selecionadas.
   * 
   * @param bandasSelecionadas 
   *   Bandas selecionadas.
   */
  public void setBandasSelecionadas(List<Banda> bandasSelecionadas) {
    this.bandasSelecionadas = bandasSelecionadas;
  }

  /**
   * Retorna as casas selecionadas.
   * 
   * @return 
   *   As casas selecionadas.
   */
  public List<Casa> getCasasSelecionadas() {
    return casasSelecionadas;
  }

  /**
   * Define as casas selecionadas.
   * 
   * @param casasSelecionadas 
   *   Casas selecionadas.
   */
  public void setCasasSelecionadas(List<Casa> casasSelecionadas) {
    this.casasSelecionadas = casasSelecionadas;
  }

  /**
   * Retorna as cidades selecionadas.
   * 
   * @return 
   *   As cidades selecionadas.
   */
  public List<Cidade> getCidadesSelecionadas() {
    return cidadesSelecionadas;
  }

  /**
   * Define as cidades selecionadas.
   * 
   * @param cidadesSelecionadas 
   *   Cidades selecionadas.
   */
  public void setCidadesSelecionadas(List<Cidade> cidadesSelecionadas) {
    this.cidadesSelecionadas = cidadesSelecionadas;
  }

  /**
   * Retorna a lista de Eventos disponíveis.
   * 
   * @return 
   *   Lista de Eventos disponíveis.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public List<Evento> getEventos() {
    return getEntidades();
  }

  /**
   * Define a referência a ser guardada a um {@link Evento}.
   * 
   * @param evento  
   *   {@link Evento} a ser guardado para operação futura.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public void setEvento(Evento evento) {
    setEntidade(evento);
  }

  /**
   * Retorna um {@link Evento} guardado.
   * 
   * @return 
   *   {@link Evento} previamente guardado através do {@link #setEvento(Evento)}.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public Evento getEvento() {
    return getEntidade();
  }

  /**
   * Workaround para permitir que uma Banda selecionada na table seja desselecionada simplesmente
   * clicando sobre ela novamente.
   * 
   * @param event 
   *   Evento de seleção.
   */
  public void bandaSelecionada(SelectEvent event) {
    if (bandasSelecionadas.size() == quantidadeBandasSelecionadas) {
      bandasSelecionadas.remove((Banda) event.getObject());
    }

    quantidadeBandasSelecionadas = bandasSelecionadas.size();
  }

  /**
   * Workaround para permitir que uma Casa selecionada na table seja desselecionada simplesmente
   * clicando sobre ela novamente.
   * 
   * @param event 
   *   Evento de seleção.
   */
  public void casaSelecionada(SelectEvent event) {
    if (casasSelecionadas.size() == quantidadeCasasSelecionadas) {
      casasSelecionadas.remove((Casa) event.getObject());
    }

    quantidadeCasasSelecionadas = casasSelecionadas.size();
  }

  /**
   * Workaround para permitir que uma Cidade selecionada na table seja desselecionada simplesmente
   * clicando sobre ela novamente.
   * 
   * @param event 
   *   Evento de seleção.
   */
  public void cidadeSelecionada(SelectEvent event) {
    if (cidadesSelecionadas.size() == quantidadeCidadesSelecionadas) {
      cidadesSelecionadas.remove((Cidade) event.getObject());
    }

    quantidadeCidadesSelecionadas = cidadesSelecionadas.size();
  }

  /**
   * Aplica os filtros
   */
  public void aplicarFiltros() {
    try {
      entidades = ((EventoServices) getEntidadeServices()).getEventos(cidadesSelecionadas,
          casasSelecionadas, bandasSelecionadas);
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Eventos filtrados",
          "Ocorreu uma execção ao tentar recuperar a lista de Eventos filtrados. Consulte o log da "
          + "aplicação para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro ao carregar Eventos filtrados", e);
    }
  }
  
  /**
   * Returna uma String vazia (caso <code>elementos</code> <code>null<code> ou <code>empty</code>)
   * ou uma representação <code>"(n)"</code> onde <code>n</code> é o tamanho de 
   * <code>elementos</code>.
   * 
   * @param elementos
   *   Coleção de elementos.
   * 
   * @return 
   *   String conforme descrito.
   */
  public String getQuantidadeFiltro(Collection elementos){
    return (elementos == null || elementos.isEmpty()) ? "" : "(" + elementos.size() + ")";
  }

  /**
   * Apenas publica o método protegido da classe mãe.
   * 
   * {@inheritDoc}
   */
  @Override
  public void carregarEntidades() {
    super.carregarEntidades();
  }
}
