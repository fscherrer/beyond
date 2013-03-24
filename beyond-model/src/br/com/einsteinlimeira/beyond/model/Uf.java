package br.com.einsteinlimeira.beyond.model;

public class Uf extends Entidade {

  private String sigla;

  private String nome;

  public Uf() {
  }

  public Uf(int id, String sigla, String nome) {
    super(id);
    this.sigla = sigla;
    this.nome = nome;
  }

  public String getSigla() {
    return sigla;
  }

  public void setSigla(String sigla) {
    this.sigla = sigla;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
