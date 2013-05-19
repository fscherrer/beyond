package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.EventoServices;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
   * Armazena os estilos selecionados.
   */
  private List<String> estilosSelecionados;

  /**
   * Armazena a quantidade de estilos selecionados.
   */
  private int quantidadeEstilosSelecionados;

  /**
   * Lista de casas disponíveis para seleção do usuário (dependerá de filtro de cidade aplicado).
   */
  private List<Casa> casas;

  /**
   * Lista de bandas disponíveis para seleção do usuário (dependerá de filtro de estilo aplicado).
   */
  private List<Banda> bandas;

  /**
   * Estilos das bandas.
   */
  private List<String> estilos;

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
   * Retorna os estilos selecionados.
   * 
   * @return 
   *   Os estilos selecionados.
   */
  public List<String> getEstilosSelecionados() {
    return estilosSelecionados;
  }

  /**
   * Define os estilos selecionados.
   * 
   * @param estilosSelecionados 
   *   Estilos selecionados.
   */
  public void setEstilosSelecionados(List<String> estilosSelecionados) {
    this.estilosSelecionados = estilosSelecionados;
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
   * clicando sobre ela novamente.<br />
   * Também delega o processamento para filtrar as casas.
   * 
   * @param event 
   *   Evento de seleção.
   */
  public void cidadeSelecionada(SelectEvent event) {
    if (cidadesSelecionadas.size() == quantidadeCidadesSelecionadas) {
      cidadesSelecionadas.remove((Cidade) event.getObject());
    }

    quantidadeCidadesSelecionadas = cidadesSelecionadas.size();

    filtrarCasas();
  }

  /**
   * Delega o processamento para filtrar as casas.
   * 
   * @param event 
   *   Evento de desseleção.
   */
  public void cidadeDesselecionada(UnselectEvent event) {
    filtrarCasas();
  }

  /**
   * Workaround para permitir que um Estilo selecionado na table seja desselecionado simplesmente
   * clicando sobre ele novamente.<br />
   * Também delega o processamento para filtrar as bandas.
   * 
   * @param event 
   *   Evento de seleção.
   */
  public void estiloSelecionado(SelectEvent event) {
    if (estilosSelecionados.size() == quantidadeEstilosSelecionados) {
      estilosSelecionados.remove((String) event.getObject());
    }

    quantidadeEstilosSelecionados = estilosSelecionados.size();

    filtrarBandas();
  }

  /**
   * Delega o processamento para filtrar as bandas.
   * 
   * @param event 
   *   Evento de desseleção.
   */
  public void estiloDesselecionado(UnselectEvent event) {
    filtrarBandas();
  }

  /**
   * Filtra as casas de acordo com as Cidades selecionadas.
   */
  private void filtrarCasas() {
    try {
      casas = ServicesFactory.getFactory().getCasaServices().getCasas(cidadesSelecionadas);
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Casas filtradas",
          "Ocorreu uma execção ao tentar recuperar a lista de Casas filtradas. Consulte o log da "
          + "aplicação para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro ao carregar Casas filtradas", e);
    }
  }

  /**
   * Filtra as bandas de acordo com os estilos selecionados.
   */
  private void filtrarBandas() {
    try {
      bandas = ServicesFactory.getFactory().getBandaServices().getBandas(estilosSelecionados);
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Bandas filtradas",
          "Ocorreu uma execção ao tentar recuperar a lista de Bandas filtradas. Consulte o log da "
          + "aplicação para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro ao carregar Bandas filtradas", e);
    }
  }

  /**
   * Aplica os filtros
   */
  public void aplicarFiltros() {
    List<Casa> casasFiltrar = null;
    List<Banda> bandasFiltrar = null;

    // casas filtradas explicitamente
    if (casasSelecionadas != null && !casasSelecionadas.isEmpty()) {
      casasFiltrar = casasSelecionadas;
    }
    // casas filtradas indiretamente através do filtro de cidades
    else if (cidadesSelecionadas != null && !cidadesSelecionadas.isEmpty()) {
      casasFiltrar = casas;
    }
    
    // bandas filtradas explicitamente
    if (bandasSelecionadas != null && !bandasSelecionadas.isEmpty()) {
      bandasFiltrar = bandasSelecionadas;
    }
    // bandas filtradas indiretamente através do filtro de estilos
    else if (estilosSelecionados != null && !estilosSelecionados.isEmpty()) {
      bandasFiltrar = bandas;
    }

    try {
      entidades = ((EventoServices) getEntidadeServices()).getEventos(casasFiltrar,
          bandasFiltrar);
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
   * Solicita o carregamento dos dados.
   */
  @PostConstruct
  private void carregaDados() {
    carregarCasas();
    carregarEstilos();
    carregarBandas();
  }

  /**
   * Carrega todas as Casas disponíveis.
   */
  public void carregarCasas() {
    try {
      casas = ServicesFactory.getFactory().getCasaServices().listar();
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Casas",
          "Ocorreu uma execção ao tentar recuperar a lista de Casas. Consulte o log da aplicação"
          + " para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro ao carregar Casas", e);
    }
  }

  /**
   * Carrega todas as Bandas disponíveis.
   */
  public void carregarBandas() {
    try {
      bandas = ServicesFactory.getFactory().getBandaServices().listar();
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Bandas",
          "Ocorreu uma execção ao tentar recuperar a lista de Bandas. Consulte o log da aplicação"
          + " para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro ao carregar Bandas", e);
    }
  }

  /**
   * Carrega todos os estilos disponíveis.
   */
  public void carregarEstilos() {
    try {
      estilos = ServicesFactory.getFactory().getBandaServices().getEstilos();
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Estilos",
          "Ocorreu uma execção ao tentar recuperar a lista de Estilos. Consulte o log da aplicação"
          + " para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro ao carregar Estilos", e);
    }
  }

  /**
   * Retorna a lista de Casas disponíveis, variando de acordo com o filtro de cidades aplicado.
   * 
   * @return 
   *   Casas.
   */
  public List<Casa> getCasas() {
    return casas;
  }

  /**
   * Retorna a lista de Bandas disponíveis, variando de acordo com o filtro de estilos aplicado.
   * 
   * @return 
   *   Bandas.
   */
  public List<Banda> getBandas() {
    return bandas;
  }

  /**
   * Retorna os estilos (a partir das Bandas) disponíveis.
   * 
   * @return 
   *   Estilos musicais.
   */
  public List<String> getEstilos() {
    return estilos;
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
  public String getQuantidadeFiltro(Collection elementos) {
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
