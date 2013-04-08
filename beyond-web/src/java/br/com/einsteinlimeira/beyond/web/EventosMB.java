package br.com.einsteinlimeira.beyond.web;

import br.com.einsteinlimeira.beyond.model.Evento;
import br.com.einsteinlimeira.beyond.web.model.dao.DAOException;
import br.com.einsteinlimeira.beyond.web.services.EventoServices;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Iron
 */
@ManagedBean
@RequestScoped
public class EventosMB implements Serializable {

  private List<Evento> eventos;
  private Evento evento;

  public EventosMB() {
    carregarEventos();
  }

  public void carregarEventos() {

    try {
      eventos = new EventoServices().getEventos();

      for (Evento evento : eventos) {
        System.out.println(evento.getCasa().getNome());
      }

    } catch (DAOException daoe) {
      FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
              FacesMessage.SEVERITY_ERROR, "Não foi possível obter a lista de Eventos",
              "Ocorreu uma execção ao tentar recuperar a lista de Eventos. Consulte o log da aplicação para"
              + " mais detalhes"));
    }
  }

  public List<Evento> getEventos() {
    return eventos;
  }

  public Evento getEvento() {
    return evento;
  }

  public void setEvento(Evento evento) {
    this.evento = evento;
  }
}
