package br.com.einsteinlimeira.beyond.model.remover;

import br.com.einsteinlimeira.beyond.model.Casa;
import br.com.einsteinlimeira.beyond.model.Contato;
import br.com.einsteinlimeira.beyond.model.Entidade;

public class ContatoCasa extends Entidade {

  private Contato contato;

  private Casa casa;

  public ContatoCasa() {
  }

  public ContatoCasa(int id, Contato contato, Casa casa) {
    super(id);
    this.contato = contato;
    this.casa = casa;
  }

  public Contato getContato() {
    return contato;
  }

  public void setContato(Contato contato) {
    this.contato = contato;
  }

  public Casa getCasa() {
    return casa;
  }

  public void setCasa(Casa casa) {
    this.casa = casa;
  }
}
