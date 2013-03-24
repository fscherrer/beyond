package br.com.einsteinlimeira.beyond.model.remover;

import br.com.einsteinlimeira.beyond.model.Banda;
import br.com.einsteinlimeira.beyond.model.Entidade;
import br.com.einsteinlimeira.beyond.model.Evento;

public class EventoBanda extends Entidade {

  private Evento evento;

  private Banda banda;

  public EventoBanda() {
  }

  public EventoBanda(int id, Evento evento, Banda banda) {
    super(id);
    this.evento = evento;
    this.banda = banda;
  }

  public Evento getEvento() {
    return evento;
  }

  public void setEvento(Evento evento) {
    this.evento = evento;
  }

  public Banda getBanda() {
    return banda;
  }

  public void setBanda(Banda banda) {
    this.banda = banda;
  }
}
