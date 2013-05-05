package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.services.EntidadeServicesException;
import br.com.einsteinlimeira.beyond.services.ServicesFactory;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 * ManagedBean para manipulação de {@link Evento}.
 */
@ManagedBean
@ViewScoped
public class EventosMB implements Serializable {

  /**
   * Armazena a lista de {@link Evento}s.
   */
  private List<Evento> eventos;

  /**
   * Conterá a referência a um {@link Evento} sendo manipulado (para exibir detalhes, por exemplo).
   */
  private Evento evento;

  /**
   * Carrega a lista de {@link Evento}s, que poderão ser obtidos a partir do {@link #getEventos()}.
   */
  @PostConstruct
  public void carregarEventos() {

    try {
      eventos = ServicesFactory.getFactory().getEventoServices().listar();
    }
    catch (EntidadeServicesException ese) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
          FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Eventos",
          "Ocorreu uma execção ao tentar recuperar a lista de Eventos. Consulte o log da aplicação para"
          + " mais detalhes"));
    }
  }

  /**
   * Retorna a lista de {@link Evento}s disponíveis.
   * 
   * @return 
   *   Lista de {@link Evento}s disponíveis.
   */
  public List<Evento> getEventos() {
    return eventos;
  }

  /**
   * Retorna um {@link Evento} guardado.
   * 
   * @return 
   *   {@link Evento} previamente guardado através do {@link #setEvento(Evento)}.
   */
  public Evento getEvento() {
    return evento;
  }

  /**
   * Define a referência a ser guardada a um {@link Evento}.
   * 
   * @param evento 
   *   {@link Evento} a ser guardado para operação futura.
   */
  public void setEvento(Evento evento) {
    this.evento = evento;
  }
}
