package br.com.einsteinlimeira.beyond.model;

public class Cidade extends Entidade {

  private String nome;

  private Uf uf;

  public Cidade() {
  }

  public Cidade(int id, String nome, Uf uf) {
    super(id);
    this.nome = nome;
    this.uf = uf;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Uf getUf() {
    return uf;
  }

  public void setUf(Uf uf) {
    this.uf = uf;
  }
}
