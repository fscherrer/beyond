package br.com.einsteinlimeira.beyond.model;

import java.util.List;

public class Musico extends Entidade {

  private String nome;

  private List<Contato> contatos;

  public Musico() {
  }

  public Musico(int id, String nome) {
    this(id, nome, null);
  }

  public Musico(int id, String nome, List<Contato> contatos) {
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

  public List<Contato> getContatos() {
    return contatos;
  }

  public void setContatos(List<Contato> contatos) {
    this.contatos = contatos;
  }
}
