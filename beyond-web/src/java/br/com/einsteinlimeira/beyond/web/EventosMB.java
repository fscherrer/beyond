package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Cidade;
import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.BandaServices;
import br.com.einsteinlimeira.beyond.services.CasaServices;
import br.com.einsteinlimeira.beyond.services.EntidadeServices;
import br.com.einsteinlimeira.beyond.services.EventoServices;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

/**
 * ManagedBean para manipulação de {@link Evento}.
 */
@ManagedBean
@ViewScoped
public class EventosMB extends BaseManagedBeanEntidade<Evento> {

  /**
   * Services de Casa.
   */
  @Inject
  private CasaServices casaServices;

  /**
   * Services de Banda.
   */
  @Inject
  private BandaServices bandaServices;

  /**
   * Services de Evento.
   */
  @Inject
  private EventoServices eventoServices;

  /**
   * ManagedBean de login
   */
  @Inject
  private LoginMB loginMB;

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
   * Lista de Eventos filtrados.
   */
  private List<Evento> eventosFiltrados;

  /**
   * Modelo para o Mapa.
   */
  private DefaultMapModel mapModel;

  /**
   * Flag para indicar se o Mapa deve ou não ser exibido (no dialog de detalhes).
   */
  private boolean exibirMapa;

  /**
   * Coordenada para centralização do Mapa.
   */
  private String coordenada;

  /**
   * {@inheritDoc}
   */
  @Override
  public EntidadeServices<Evento> getEntidadeServices() {
    return eventoServices;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Evento getNovaEntidade() {
    Evento evento = new Evento();

    if (!loginMB.isAdministrador()) {
      evento.setCasa(loginMB.getUsuarioAutenticado().getCasa());
    }

    return evento;
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
   * Retorna a lista de Eventos filtrados.
   * 
   * @return 
   *   Eventos filtrados.
   */
  public List<Evento> getEventosFiltrados() {
    return eventosFiltrados;
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
   * Retorna o Modelo para o Mapa do evento selecionado.
   * 
   * @return 
   *   Modelo para o Mapa.
   */
  public MapModel getMapModel() {
    return mapModel;
  }

  /**
   * Retorna se o Mapa do Endereço da Casa do Evento deve ser ou não exibido.
   * 
   * @return 
   *   <code>True</code> se deve ser exibido.
   */
  public boolean isExibirMapa() {
    return exibirMapa;
  }

  public void prepararExibicaoDetalhes() {
    exibirMapa = false;
    mapModel = null;

    coordenada = "-22.023527,-48.240967";
    
    String coordenadaEnderecoCasa = entidade.getCasa().getEndereco().getCoordenada();

    if (coordenadaEnderecoCasa != null && !coordenadaEnderecoCasa.trim().isEmpty()) {
      Marker marker;
      String[] latLng = coordenadaEnderecoCasa.split(",");

      try {
        marker = new Marker(new LatLng(
            Double.parseDouble(latLng[0]),
            Double.parseDouble(latLng[1])),
            entidade.getCasa().getNome());

        mapModel = new DefaultMapModel();
        mapModel.addOverlay(marker);

        coordenada = coordenadaEnderecoCasa;
        
        exibirMapa = true;
      }
      catch (NumberFormatException nfe) {
        // não mostra map
      }
    }
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
      casas = casaServices.getCasas(cidadesSelecionadas);
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
      bandas = bandaServices.getBandas(estilosSelecionados);
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
      eventosFiltrados = ((EventoServices) getEntidadeServices()).getEventos(casasFiltrar,
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
    aplicarFiltros();
  }

  /**
   * Carrega todas as Casas disponíveis.
   */
  public void carregarCasas() {
    try {
      casas = casaServices.listar();
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
      bandas = bandaServices.listar();
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
      estilos = bandaServices.getEstilos();
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
   * Carrega os eventos conforme o usuário logado.
   * 
   * {@inheritDoc}
   */
  @Override
  public void carregarEntidades() {
    // só carrega a lista de eventos aqui se for a página de eventos (para manipulação)
    if (!FacesContext.getCurrentInstance().getViewRoot().getViewId().endsWith("eventos.xhtml")) {
      return;
    }

    try {
      List<Casa> casasFiltrar = null;

      // se é o administrador, serão listados todos os eventos
      // senão serão listados apenas os eventos da casa do usuário
      if (!loginMB.isAdministrador()) {
        casasFiltrar = new ArrayList<Casa>(1);
        casasFiltrar.add(loginMB.getUsuarioAutenticado().getCasa());
      }

      entidades = eventoServices.getEventos(casasFiltrar, null);
    }
    catch (Exception e) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Eventos",
          "Ocorreu uma execção ao tentar recuperar a lista de Eventos. Consulte o log da "
          + "aplicação para mais detalhes"));
      LOGGER.log(Level.SEVERE, "Erro não experado", e);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void editar() {
    super.editar();
    bandasSelecionadas = entidade.getBandas();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void incluir() {
    super.incluir();
    bandasSelecionadas = null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void salvar() {
    entidade.setBandas(bandasSelecionadas);
    super.salvar();
  }

  /**
   * Retorna a lista de Eventos carregados.
   * 
   * @return 
   *   Lista de Eventos carregados.
   */
  // criado devido aos warnings e falta de code completion do NB com o tipo genérico
  public List<Evento> getEventos() {
    return entidades;
  }

  /**
   * Retorna a coordenada para centralização do Mapa.
   * 
   * @return 
   *   Coordenada para centralização.
   */
  public String getCoordenada() {
    return coordenada;
  }
}
