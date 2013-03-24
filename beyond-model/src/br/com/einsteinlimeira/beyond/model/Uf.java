package br.com.einsteinlimeira.beyond.model;

import java.util.List;

public class Uf extends Entidade {

  private String sigla;

  private String nome;

  private List<Cidade> cidades;

  public Uf() {
  }

  public Uf(int id, String sigla, String nome, List<Cidade> cidades) {
    super(id);
    this.sigla = sigla;
    this.nome = nome;
    this.cidades = cidades;
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

  public List<Cidade> getCidades() {
    return cidades;
  }

  public void setCidades(List<Cidade> cidades) {
    this.cidades = cidades;
  }
}
