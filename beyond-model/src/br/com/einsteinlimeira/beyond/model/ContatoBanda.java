package br.com.einsteinlimeira.beyond.model;

public class ContatoBanda extends Entidade {

  private Contato contato;

  private Banda banda;

  public ContatoBanda() {
  }

  public ContatoBanda(int id, Contato contato, Banda banda) {
    super(id);
    this.contato = contato;
    this.banda = banda;
  }

  public Contato getContato() {
    return contato;
  }

  public void setContato(Contato contato) {
    this.contato = contato;
  }

  public Banda getBanda() {
    return banda;
  }

  public void setBanda(Banda banda) {
    this.banda = banda;
  }
}
