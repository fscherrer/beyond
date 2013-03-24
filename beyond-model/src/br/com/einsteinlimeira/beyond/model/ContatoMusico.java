package br.com.einsteinlimeira.beyond.model;

public class ContatoMusico extends Entidade {

  private Musico musico;

  private Contato contato;

  public ContatoMusico() {
  }

  public ContatoMusico(int id, Musico musico, Contato contato) {
    super(id);
    this.musico = musico;
    this.contato = contato;
  }

  public Musico getMusico() {
    return musico;
  }

  public void setMusico(Musico musico) {
    this.musico = musico;
  }

  public Contato getContato() {
    return contato;
  }

  public void setContato(Contato contato) {
    this.contato = contato;
  }
}
