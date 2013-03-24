package br.com.einsteinlimeira.beyond.model;

import java.util.List;

public class Musico extends Entidade {

  private String nome;

  private List<ContatoMusico> contatos;

  public Musico() {
  }

  public Musico(int id, String nome) {
    this(id, nome, null);
  }

  public Musico(int id, String nome, List<ContatoMusico> contatos) {
    super(id);
    this.nome = nome;
    this.contatos = contatos;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public List<ContatoMusico> getContatos() {
    return contatos;
  }

  public void setContatos(List<ContatoMusico> contatos) {
    this.contatos = contatos;
  }
}
