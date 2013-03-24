package br.com.einsteinlimeira.beyond.model;

import java.util.Date;

public class Banda extends Entidade {

  private String nome;

  private Date dataformacao;

  public Banda() {
  }

  public Banda(int id, String nome, Date dataformacao) {
    super(id);
    this.nome = nome;
    this.dataformacao = dataformacao;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public Date getDataformacao() {
    return dataformacao;
  }

  public void setDataformacao(Date dataformacao) {
    this.dataformacao = dataformacao;
  }
}
