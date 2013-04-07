package br.com.einsteinlimeira.beyond.model;

import java.util.Date;

public class Evento extends Entidade {

  private Date datahora;
  private String nome;
  private double valor;
  private Casa casa;
  private Banda banda;

  public Evento() {
  }

  public Evento(int id, String nome, Date datahora, double valor, Casa casa, Banda banda) {
    super(id);
    this.datahora = datahora;
    this.valor = valor;
    this.casa = casa;
    this.banda = banda;
    this.nome = nome;
  }

  public Date getDatahora() {
    return datahora;
  }

  public void setDatahora(Date datahora) {
    this.datahora = datahora;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }

  public Casa getCasa() {
    return casa;
  }

  public void setCasa(Casa casa) {
    this.casa = casa;
  }

  public Banda getBanda() {
    return banda;
  }

  public void setBanda(Banda banda) {
    this.banda = banda;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }  
}
